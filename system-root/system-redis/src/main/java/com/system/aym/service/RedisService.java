package com.system.aym.service;

import redis.clients.jedis.Jedis;

public interface RedisService {
    /**
     * @return
     */
    public Jedis getResource();

    /**
     * @param jedis
     */
    public void closeJedis(Jedis jedis);

    /**
     * 设置key，并存储数据
     *
     * @param key
     * @param value
     */
    public String set(String key, String value);

    /**
     * 设置key的有限期，并存储数据
     *
     * @param key
     * @param liveTime
     * @param value
     */
    public String setex(String key, int liveTime, String value);

    /**
     * 若key不存在，则存储
     *
     * @param key
     * @param value
     */
    public String setnx(String key, String value);

    /**
     * 根据key，获取值
     *
     * @param key
     * @return
     */
    public String get(String key);

    /**
     * 根据key，删除值
     *
     * @param key
     */
    public String delete(String key);

    /**
     * 根据key，设置有效期
     *
     * @param key
     * @param liveTime
     * @return
     */
    public long expire(String key, int liveTime);

    /**
     * 根据key，追加数据，并存储数据
     *
     * @param key
     * @param value
     */
    public String append(String key, String value);

    /**
     * 根据key，截取value的值
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public String getrange(String key, long start, long end);

    /**
     * 查询key是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key);

}
