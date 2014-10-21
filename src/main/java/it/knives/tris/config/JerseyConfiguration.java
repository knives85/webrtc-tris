package it.knives.tris.config;

import static java.util.logging.Logger.getLogger;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Knives on 19/10/14.
 */
public class JerseyConfiguration extends ResourceConfig {
    private final Logger log = getLogger(getClass().getName());

    @Inject
    public JerseyConfiguration(ServiceLocator serviceLocator, ServletContext servletContext) {
        log.info("Creating JerseyConfiguration");
        packages("it.knives.tris.resources");
        register(MyObjectMapper.class);
        register(JacksonFeature.class);
    }

}