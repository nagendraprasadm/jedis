package org.nm.redisclient.jedis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:redis.properties")
public class RedisProperties {

	@Value("${redis.server}")
	private String server;

	@Value("${redis.port}")
	private String port;

	public String getServer() {
		return server;
	}

	public String getPort() {
		return port;
	}
}
