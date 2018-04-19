package com.wu.common.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisClient {
    private static Logger logger = LoggerFactory.getLogger(RedisClient.class);

    private RedisTemplate<String, Object> redisTemplate;
    public final Lock lock;
    public final Queue queue;

    public RedisClient(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.lock = new Lock(redisTemplate);
        this.queue = new Queue(this, redisTemplate);
    }

    public void putString(String key, String value) {
        this.putObject(key, value);
    }

    public void putString(String key, String value, long timeout) {
        this.putString(key, value);
        this.redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    public String getString(String key) {
        Object obj = this.getObject(key);
        return obj instanceof String?(String)obj:null;
    }

    public void delete(String... keys) {
        String[] arr$ = keys;
        int len$ = keys.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String key = arr$[i$];
            if(key != null) {
                this.redisTemplate.delete(key);
            }
        }

    }

    public Object getObject(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }

    public void putObject(String key, Object value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    public void putObject(String key, Object value, long timeout) {
        this.putObject(key, value);
        this.redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }
    //------------------------------------------------------------------------------------
    //Map操作
    public void putHash(String key,Map map, long timeout){
        this.redisTemplate.opsForHash().putAll(key,map);
        this.redisTemplate.expire(key,timeout, TimeUnit.SECONDS);
    }
    public void putHash(String key,Map map){
        this.redisTemplate.opsForHash().putAll(key,map);
    }
    //获取map集合
    public Map getHash(String key){
        return this.redisTemplate.opsForHash().entries(key);
    }
    //获取map集合value值
    public List getHashValues(String key){
        return this.redisTemplate.opsForHash().values(key);
    }
    //获取map集合key值
    public Set getHashKeys(String key){
        return this.redisTemplate.opsForHash().keys(key);
    }
    //List操作------------------------------------------------------------------------------------------------
    //缓存List,set集合
    public void pushList(String key, Object value) {
        this.redisTemplate.opsForList().rightPush(key, value);
    }
    //获取list,set集合（最好leftPush用leftPoP遍历获取，rightPush用rightPoP遍历获取）
    public Object popList(String key) {
        return this.redisTemplate.opsForList().rightPop(key);
    }

    public Object indexFirstList(String key) {
        return this.redisTemplate.opsForList().index(key, 0L);
    }

    //获取总条数
    public long listSize(String key) {
        return this.redisTemplate.opsForList().size(key).longValue();
    }

    //获取list缓存
    public List<Object> getList(String key, Long size) {
        return this.redisTemplate.opsForList().range(key, 0L, size.longValue());
    }

    public void pushList(String key, Object value, Long timeout) {
        this.redisTemplate.opsForList().rightPush(key, value);
        this.redisTemplate.expire(key, timeout.longValue(), TimeUnit.SECONDS);
    }
    //--------------------------------------------------------------------------------------
    public boolean containsKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Throwable t) {
            logger.error("判断缓存存在失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }
}
