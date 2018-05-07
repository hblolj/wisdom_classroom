package com.yszn.security.server;

import com.yszn.security.app.jwt.MyJwtTokenEnhancer;
import com.yszn.security.core.properties.base.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author: hblolj
 * @Date: 2018/5/4 13:48
 * @Description:
 * @Version: 1.0
 **/
@Configuration
public class TokenStoreConfig {


    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @ConditionalOnProperty(prefix = "hblolj.security.oauth2", name = "storeType", havingValue = "redis")
    public TokenStore redisTokenStore(){return new RedisTokenStore(redisConnectionFactory);}

    /**
     * 使用redis存储token的配置，只有在hblolj.security.oauth2.tokenStore配置为redis时生效
     */
//    @Configuration
//    @ConditionalOnProperty(prefix = "yszn.security.oauth2", name = "tokenStore", havingValue = "redis")
//    public static class RedisConfig{
//
//    }

    @Configuration
    @ConditionalOnProperty(prefix = "yszn.security.oauth2", name = "storeType", havingValue = "jwt", matchIfMissing = true)
    public static class JwtTokenConfig{

        @Autowired
        private SecurityProperties securityProperties;

        @Bean
        public TokenStore JwtTokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            jwtAccessTokenConverter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
            return jwtAccessTokenConverter;
        }

        @Bean
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer(){
            return new MyJwtTokenEnhancer();
        }
    }
}
