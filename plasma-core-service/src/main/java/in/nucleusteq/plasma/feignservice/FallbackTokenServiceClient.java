package in.nucleusteq.plasma.feignservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.Response;
import in.nucleusteq.plasma.dto.common.TokenRequest;
import in.nucleusteq.plasma.exception.TokenServiceException;

/**
* This class is used for give the response when the token microservice is
* unable to give the token related to a particular user.
* @author abhis
*
*/
@Component
public class FallbackTokenServiceClient implements TokenClientService {

    /**
     * this is the logger for the message.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FallbackTokenServiceClient.class);

    @Override
    public Response getToken(TokenRequest tokenRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getToken'");
    }

    @Override
    public Response fetchData(String authorizationToken) throws TokenServiceException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(" Error While Calling token Service Fetch Data");
    }


}