package com.wu.common.redis;

/**
 * Created by MJN on 2018/4/19.
 */
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;

public class Lock {
    private long lockTimeout = 5L;
    private long lockSleepTime = 100L;
    private RedisTemplate<String, Object> redisTemplate;

    Lock(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void lock(String key) {
        this.lock(key, this.lockTimeout, this.lockSleepTime);
    }

    public void lock(String key, long lockTimeout, long lockSleepTime) {
        while(true) {
            long time = System.currentTimeMillis();
            boolean isSet = this.redisTemplate.opsForValue().setIfAbsent(key, Long.valueOf(time)).booleanValue();
            if(isSet) {
                this.redisTemplate.expire(key, lockTimeout, TimeUnit.SECONDS);
                return;
            }

            if(this.redisTemplate.getExpire(key).longValue() < 0L) {
                this.redisTemplate.expire(key, lockTimeout, TimeUnit.SECONDS);
            }

            try {
                Thread.sleep(lockSleepTime);
            } catch (InterruptedException var10) {
                var10.printStackTrace();
            }
        }
    }

    public boolean lock(String key, long lockTimeout) {
        long time = System.currentTimeMillis();
        boolean isSet = this.redisTemplate.opsForValue().setIfAbsent(key, Long.valueOf(time)).booleanValue();
        if(isSet) {
            this.redisTemplate.expire(key, lockTimeout, TimeUnit.SECONDS);
            return true;
        } else {
            if(this.redisTemplate.getExpire(key).longValue() < 0L) {
                this.redisTemplate.expire(key, lockTimeout, TimeUnit.SECONDS);
            }

            return false;
        }
    }

    public void unlock(String key) {
        this.redisTemplate.delete(key);
    }
}
