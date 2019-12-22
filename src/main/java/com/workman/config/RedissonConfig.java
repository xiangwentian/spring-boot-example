package com.workman.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.maxWaitMillis}")
    private int maxWaitMillis;

    @Value("${spring.redis.maxTotal}")
    private int maxTotal;

    @Value("${spring.redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.redis.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.maxIdle}")
    private int maxIdle;

    private static final String REDIS_PROTOCOL = "redis://";

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public static String getRedisProtocol() {
        return REDIS_PROTOCOL;
    }

    @Override
    public java.lang.String toString() {
        return "RedissonConfig [host=" + host + ", port=" + port + ", maxWaitMillis="
                + maxWaitMillis + ", maxTotal=" + maxTotal + ", testOnBorrow=" + testOnBorrow + ", testOnReturn="
                + testOnReturn + ", timeout=" + timeout + ", maxIdle=" + maxIdle + "]";

    }

    /**
     * 获取redisson配置信息
     * @return
     */
    public Config getConfig() {
        Config config = new Config();
        config.useSingleServer().setAddress(REDIS_PROTOCOL + host + ":" + port).setDatabase(3).setConnectionMinimumIdleSize(maxIdle).setConnectionPoolSize(maxTotal).setConnectTimeout(timeout);
        log.info("======config:{},{}", host, port);
        return config;
    }
}
