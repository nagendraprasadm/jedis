package org.nm.redisclient.jedis.pool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nm.redisclient.spring.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class JedisConnectionServiceTest extends TestCase {

	@Autowired
	private JedisConnectionService jedisConnectionService;

	@Test
	public void testJedis() {
		assertNotNull(jedisConnectionService.getJedis());
	}
}
