package org.nm.redisclient.jedis.pool;

import javax.annotation.PostConstruct;

import org.nm.redisclient.jedis.RedisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component("jedisConnectionService")
public class JedisConnectionService {

	private Logger logger = LoggerFactory.getLogger(JedisConnectionService.class);

	private JedisPool jedisPool;

	@Autowired
	private RedisProperties redisProperties;

	public Jedis getJedis() {
		return this.jedisPool.getResource();
	}

	public void closeJedis(Jedis jedis) {
		jedis.close();
	}

	public void closeJedisPool() {
		this.jedisPool.close();
	}

	public String numIdle() {
		return  Integer.toString(jedisPool.getNumIdle());
	}

	@PostConstruct
	public void init() {
		logger.info("Got Redis Properties - Server : " + redisProperties.getServer() + ", Port : "
				+ redisProperties.getPort());
		jedisPool = new JedisPool(redisProperties.getServer(), Integer.parseInt(redisProperties.getPort()));
	}
}
