package com.fynd.config.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {

    private String host;

    private int maxTotal;

    private int maxIdle;

    private int minIdle;

    private int idleTime;

    private int eviction;

    private int tests;
}
