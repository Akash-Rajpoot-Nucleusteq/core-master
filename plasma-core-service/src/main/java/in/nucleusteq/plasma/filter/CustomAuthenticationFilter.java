package in.nucleusteq.plasma.filter;

import java.io.IOException;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import feign.Response;
import feign.form.util.CharsetUtil;
import in.nucleusteq.plasma.constants.TokenConstant;
import in.nucleusteq.plasma.exception.TokenServiceException;
import in.nucleusteq.plasma.exception.UnauthorizedAccessException;
import in.nucleusteq.plasma.feignservice.TokenClientService;
import in.nucleusteq.plasma.entity.TokenUser;
import in.nucleusteq.plasma.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * CustomAuthenticationFilter
 */
@Configuration
public class CustomAuthenticationFilter extends GenericFilterBean {

    @Autowired
    private TokenClientService tokenClientService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private static final Gson GSON = new Gson();

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        UserDetails userDetails = null;
        String authTokenHeader = "Authorization";
        String authToken = request.getHeader(authTokenHeader);

        if (!Strings.isNullOrEmpty(authToken)) {
            try {
                userDetails = fetchData(authToken);
            } catch (UnauthorizedAccessException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, TokenConstant.TOKEN_EXPIRED);
            } catch (TokenServiceException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Fatal error while validating the token");
            }
        }
        // need to add modular function.
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void setResponseAttributesforUnauthorizedAcess(HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Headers", "Accept");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS,POST,GET,PUT");
        response.setHeader("Content-type", "application/json; charset=utf-8");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
    }

    private UserDetails fetchData(String authToken)
            throws TokenServiceException, UnauthorizedAccessException, JsonSyntaxException, JsonIOException,
            IOException {

        Response response = tokenClientService.fetchData(authToken);
        if (response.status() == HttpStatus.UNAUTHORIZED.value()) {
            LOGGER.error("Token is expired, throwing IdentityUnauthorizedException");
            throw new UnauthorizedAccessException(TokenConstant.TOKEN_EXPIRED);
        }
        if (response.status() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            LOGGER.error("Internal server error from token service, throwing TokenServiceException");
            throw new TokenServiceException(TokenConstant.INTERNAL_SERVER_ERROR);
        }
        TokenUser tokenUser = GSON.fromJson(response.body().asReader(CharsetUtil.UTF_8), TokenUser.class);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(tokenUser.getEmail());
        return userDetails;
    }
}