package org.nm.redisclient;

import org.nm.redisclient.jedis.pool.JedisConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import redis.clients.jedis.Jedis;

/**
 * Hello world!
 *
 */
public class App {

	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
		appContext.scan("org.nm.redisclient");
		appContext.refresh();
		JedisConnectionService connService = (JedisConnectionService) appContext.getBean("jedisConnectionService");
		Jedis jedis = connService.getJedis();
		jedis.set("TestKey", "TestValue");
		logger.info("Value for String key is - [{}]", jedis.get("TestKey"));
		jedis.close();
		logger.info("Jedis Pool - [{}]", connService.numIdle());
		appContext.close();
	}

}
