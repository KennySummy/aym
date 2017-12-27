package com.system.aym.service.impl;

import com.system.aym.service.RedisService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements RedisService {

    private static Logger logger = Logger.getLogger(RedisServiceImpl.class);

    @Resource
    private JedisPool jedisPool;

    @Override
    public Jedis getResource() {
        return jedisPool.getResource();
    }

    @Override
    public void closeJedis(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.set(key, value);
            logger.info("Redis set success - " + key + ", value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis set error: " + e.getMessage() + " - " + key + ", value:" + value);
        } finally {
            closeJedis(jedis);
        }
        return "SUCCESS";
    }

    @Override
    public String setex(String key, int liveTime, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.setex(key, liveTime, value);
            logger.info("Redis setex success - " + key + ", value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis setex error: " + e.getMessage() + " - " + key + ", value:" + value);
        } finally {
            closeJedis(jedis);
        }
        return "SUCCESS";
    }

    @Override
    public String setnx(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.setnx(key, value);
            logger.info("Redis setnx success - " + key + ", value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis setnx error: " + e.getMessage() + " - " + key + ", value:" + value);
        } finally {
            closeJedis(jedis);
        }
        return "SUCCESS";
    }

    @Override
    public String get(String key) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.get(key);
            logger.info("Redis get success - " + key + ", value:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis set error: " + e.getMessage() + " - " + key + ", value:" + result);
        } finally {
            closeJedis(jedis);
        }
        return result;
    }

    @Override
    public String delete(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.del(key);
            logger.info("Redis delete success - " + key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis delete error: " + e.getMessage() + " - " + key);
        } finally {
            closeJedis(jedis);
        }
        return "SUCCESS";
    }

    @Override
    public long expire(String key, int liveTime) {
        long flag = 0l;
        Jedis jedis = null;
        try {
            jedis = getResource();
            boolean isExists = jedis.exists(key);
            if (isExists) {
                flag = jedis.expire(key, liveTime);
                logger.info("Redis expire success - " + key);
            } else {
                logger.info("Redis expire error - " + key + " is null !");
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis expire error: " + e.getMessage() + " - " + key);
        } finally {
            closeJedis(jedis);
        }
        return flag;
    }

    @Override
    public String append(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            boolean isExists = jedis.exists(key);
            if (isExists) {
                jedis.append(key, value);
                logger.info("Redis append success - " + key + ", value:" + value);
            } else {
                logger.info("Redis append error - " + key + " is null !");
                return "EROOR";
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis append error: " + e.getMessage() + " - " + key);
        } finally {
            closeJedis(jedis);
        }
        return "SUCCESS";
    }

    @Override
    public String getrange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            boolean isExists = jedis.exists(key);
            if (isExists) {
                jedis.getrange(key, start, end);
                logger.info("Redis getrange success - " + key);
            } else {
                logger.info("Redis getrange error - " + key + " is null !");
                return "EROOR";
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis getrange error: " + e.getMessage() + " - " + key);
        } finally {
            closeJedis(jedis);
        }
        return "SUCCESS";
    }

    @Override
    public boolean exists(String key) {
        boolean flag = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            flag = jedis.exists(key);
            logger.info("Redis exists success - " + key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis exists error: " + e.getMessage() + " - " + key);
        } finally {
            closeJedis(jedis);
        }
        return flag;
    }

}
