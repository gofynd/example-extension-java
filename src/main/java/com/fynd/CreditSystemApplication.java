package com.fynd;

import com.fynd.extension.model.Extension;
import com.fynd.extension.model.ExtensionCallback;
import com.fynd.extension.model.ExtensionProperties;
import com.fynd.extension.storage.RedisStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import redis.clients.jedis.JedisPool;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fynd.**", "com.sdk.**"})
public class CreditSystemApplication {
    private static final String REDIS_KEY = "ext_sample";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExtensionProperties extensionProperties;

    @Autowired
    @Qualifier("redis-pool")
    private JedisPool jedis;

    ExtensionCallback callbacks = new ExtensionCallback((fdkSession) -> {
        logger.info("In auth callback");
        return extensionProperties.getBase_url() + "/company/" + fdkSession.getCompany_id();
    }, (context) -> {
        logger.info("In install callback");
        return extensionProperties.getBase_url();
    }, (fdkSession) -> {
        logger.info("In uninstall callback");
        return extensionProperties.getBase_url();
    });

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(CreditSystemApplication.class, args);
    }

    @Bean
    @DependsOn({"redis-pool"})
    public com.fynd.extension.model.Extension getExtension() {
        Extension extension = new Extension();
        return extension.initialize(extensionProperties, new RedisStorage(jedis, REDIS_KEY),
            callbacks);
    }
}
