package com.fynd.sample;

import com.fynd.extension.model.Extension;
import com.fynd.extension.model.ExtensionCallback;
import com.fynd.extension.model.ExtensionProperties;
import com.fynd.extension.storage.RedisStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import redis.clients.jedis.JedisPool;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.fynd.**", "com.gofynd", "com.sdk.**"})
public class SampleApplication {

    private static final String REDIS_KEY = "ext_sample";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExtensionProperties extensionProperties;

    @Autowired
    @Qualifier("redis-pool")
    JedisPool jedis;

    ExtensionCallback callbacks = new ExtensionCallback((fdkSession) -> {
        logger.info("In auth callback");
        return extensionProperties.getBaseUrl() + "/company/" + "61";
    }, (context) -> {
        logger.info("In install callback");
        return extensionProperties.getBaseUrl();
    }, (fdkSession) -> {
        logger.info("In uninstall callback");
        return extensionProperties.getBaseUrl();
    }, (fdkSession) -> {
        logger.info("In auto-install callback");
        return extensionProperties.getBaseUrl();
    });

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(SampleApplication.class, args);
    }

    @Bean
    @DependsOn({"redis-pool"})
    public com.fynd.extension.model.Extension getExtension() {
        Extension extension = new Extension();
        return extension.initialize(extensionProperties, new RedisStorage(jedis, REDIS_KEY),
                                    callbacks);
    }
}
