package com.example.UserService.Config.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import java.io.IOException;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
    private OAuth2AuthorizedClientManager manager;
    private Logger logger= LoggerFactory.getLogger(RestTemplateInterceptor.class);

    public RestTemplateInterceptor(OAuth2AuthorizedClientManager manager) {
        this.manager = manager;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        OAuth2AuthorizeRequest authRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId("my-internal-client")
                .principal("internal") // Just a name for the principal in client_credentials
                .build();

        var authorizedClient = manager.authorize(authRequest);

        if (authorizedClient == null || authorizedClient.getAccessToken() == null) {
            logger.error("Authorization failed: No authorized client or token returned. Check client credentials and scopes.");
            throw new IllegalStateException("Failed to authorize client with Okta.");
        }

        String token = authorizedClient.getAccessToken().getTokenValue();
        logger.info("RestTemplate interceptor: Token: {}", token);

        request.getHeaders().add("Authorization", "Bearer " + token);
        return execution.execute(request, body);
    }

}
