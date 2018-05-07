package com.yszn.security.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yszn.security.core.support.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * @author: hblolj
 * @Date: 2018/5/7 10:18
 * @Description:
 * @Version: 1.0
 **/
@Component("webResponseExceptionTranslator")
public class MyWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity responseEntity = super.translate(e);
        OAuth2Exception body = (OAuth2Exception) responseEntity.getBody();
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(responseEntity.getHeaders().toSingleValueMap());
        // do something with header or response
        if(401==responseEntity.getStatusCode().value()){
            JsonData<String> r = new JsonData<>(1024, e.getMessage(), "Invalid access token");
            return new ResponseEntity(objectMapper.writeValueAsString(r), headers, responseEntity.getStatusCode());
        }else{
            return new ResponseEntity(body, headers, responseEntity.getStatusCode());
        }

    }
}
