package com.fynd.sample.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "redis")
@Getter
@Setter
public class RedisProperties {

    private String host;

    private int maxTotal;

    private int maxIdle;

    private int minIdle;

    private int idleTime;

    private int eviction;

    private int tests;
}
