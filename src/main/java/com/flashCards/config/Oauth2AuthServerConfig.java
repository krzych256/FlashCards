package com.flashCards.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class Oauth2AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${flash.card.oauth2.clientId}")
    String clientId;

    @Value("${flash.card.oauth2.secret}")
    String clientSecret;

    @Value("${flash.card.oauth2.tokenValidityInSeconds}")
    int tokenValiditySeconds;

    @Value("${flash.card.oauth2.refreshTokenValidityInSeconds}")
    int refreshTokenValiditySeconds;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        clients.withClientDetails(jdbcClientDetailsService);
    }

//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//
////        clients
////                .jdbc(dataSource)
////                .withClient(clientId)
//////                .secret(clientSecret)
////                .secret(passwordEncoder.encode(clientSecret))
////                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
////                .accessTokenValiditySeconds(tokenValiditySeconds)
////                .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
////                .scopes("read");
//
//        clients
//                .inMemory()
//                .withClient(clientId)
////                .secret(frontendClientSecret) //TODO
//                .secret(passwordEncoder.encode(clientSecret))
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//                .accessTokenValiditySeconds(tokenValiditySeconds)
//                .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
//                .scopes("read");
//    }

//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints
//                .tokenStore(tokenStore())
//                .authenticationManager(authenticationManager);
//    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource);
        return new JwtTokenStore(accessTokenConverter());
    }
}
