package org.nm.redisclient.jedis;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.nm.redisclient.jedis.pool.JedisConnectionService;
import org.nm.redisclient.spring.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class JedisStringTest {

	private static final Logger logger = LoggerFactory.getLogger(JedisStringTest.class);

	@Rule
	public TestName testName = new TestName();

	@Autowired
	private JedisConnectionService jedisConnectionService;

	private Jedis jedis;

	@Before
	public void setupBefore() {
		jedis = jedisConnectionService.getJedis();
	}

	@Test
	public void testSimpleStringSet() {
		String key = "TestKey";
		String value = "TestValue";
		Object statusCode = jedis.set(key, value);
		logger.info("Test Name - [{}] , Status Code  after setting string - [{}]", testName.getMethodName(),
				statusCode);
		String cacheValue = jedis.get(key);
		assertEquals(value, cacheValue);
	}

	@After
	public void closeJedis() {
		jedisConnectionService.closeJedis(jedis);
	}

}
