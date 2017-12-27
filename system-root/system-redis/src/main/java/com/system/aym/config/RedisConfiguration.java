package com.system.aym.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

@Configuration
public class RedisConfiguration {

    @Bean(name = "jedis.pool")
    @Resource
    public JedisPool jedisPool(@Qualifier("jedis.pool.config") JedisPoolConfig config,
                               @Value("${jedis.pool.host}") String host,
                               @Value("${jedis.pool.port}") int port,
                               @Value("${jedis.pool.timeout}") int timeout) {
        // @Value("${jedis.pool.password}") String password) {
        return new JedisPool(config, host, port, timeout);
    }

    @Bean(name = "jedis.pool.config")
    public JedisPoolConfig jedisPoolConfig(@Value("${jedis.pool.config.maxTotal}") int maxTotal,
                                           @Value("${jedis.pool.config.maxIdle}") int maxIdle,
                                           @Value("${jedis.pool.config.minIdle}") int minIdle,
                                           @Value("${jedis.pool.config.maxWaitMillis}") int maxWaitMillis,
                                           @Value("${jedis.pool.config.testOnBorrow}") boolean testOnBorrow,
                                           @Value("${jedis.pool.config.testOnReturn}") boolean testOnReturn,
                                           @Value("${jedis.pool.config.testWhileIdle}") boolean testWhileIdle,
                                           @Value("${jedis.pool.config.timeBetweenEvictionRunsMillis}") int timeBetweenEvictionRunsMillis,
                                           @Value("${jedis.pool.config.numTestsPerEvictionRun}") int numTestsPerEvictionRun,
                                           @Value("${jedis.pool.config.minEvictableIdleTimeMillis}") int minEvictableIdleTimeMillis) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        config.setTestWhileIdle(testWhileIdle);
        config.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        config.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        return config;
    }

}
