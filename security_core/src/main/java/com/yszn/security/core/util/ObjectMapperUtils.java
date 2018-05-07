package com.yszn.security.core.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: hblolj
 * @Date: 2018/5/7 13:56
 * @Description:
 * @Version: 1.0
 **/
public class ObjectMapperUtils {

    public static JavaType getCollectionType(Class<?> collectionClass, ObjectMapper mapper, Class<?>... elementClasses) {
         return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
