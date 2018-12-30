package org.nm.redisclient.spring;

import org.nm.redisclient.jedis.pool.JedisConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component("contextAware")
public class ContextAware implements ApplicationContextAware, ApplicationListener<ContextClosedEvent> {

	private static final Logger logger = LoggerFactory.getLogger(ContextAware.class);

	private ApplicationContext appContext;

	@Autowired
	private JedisConnectionService jedisConnectionService;

	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		this.appContext = appContext;

	}

	public String getEnvironmentString() {
		return appContext.getEnvironment().toString();
	}

	public void onApplicationEvent(ContextClosedEvent event) {
		logger.info("Context Shutdown event called");
		jedisConnectionService.closeJedisPool();
	}
}
