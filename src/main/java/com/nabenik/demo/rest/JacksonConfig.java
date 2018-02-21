package com.nabenik.demo.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * This class configures Jackson features for serialization and deserialization,
 * at this time in does the following:</br>
 * <ul>
 * <li>Disables null serialization</li> 
 * </ul>
 * 
 * @author tuxtor
 *
 */
@Provider
public class JacksonConfig implements ContextResolver<ObjectMapper> {
    
	@Inject
	Logger logger;
    
    @Override
    public ObjectMapper getContext(Class<?> type) {
        logger.log(Level.WARNING, "Configuring Jackson");
        ObjectMapper mapper = new ObjectMapper();
        
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        mapper.configure(SerializationFeature.EAGER_SERIALIZER_FETCH, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.CLOSE_CLOSEABLE, true);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION); 

        return mapper;
    }
}