package in.nucleusteq.plasma.feignservice;

import org.springframework.cloud.openfeign.FeignClient;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import in.nucleusteq.plasma.dto.common.TokenRequest;
import in.nucleusteq.plasma.exception.TokenServiceException;

@FeignClient(name = "tokenClient", url = "http://localhost:9099", fallback = FallbackTokenServiceClient.class, configuration = TokenClientConfiguration.class)
public interface TokenClientService {

	@RequestLine("POST /plasma/token/generate-custom-token")
	Response getToken(TokenRequest tokenRequest);

	@RequestLine("GET /plasma/token/fetchdata")
	@Headers("Authorization: {token}")
	Response fetchData(@Param("token") String authorizationToken) throws TokenServiceException;
}