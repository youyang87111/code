package com.pax.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import net.spy.memcached.ConnectionFactoryBuilder.Locator;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.DefaultHashAlgorithm;
import net.spy.memcached.FailureMode;
import net.spy.memcached.spring.MemcachedClientFactoryBean;
import net.spy.memcached.transcoders.SerializingTranscoder;

@Configuration
public class MemcachedConfig {
	
	@Value("${memcached.servers:127.0.0.1:11211}")
	private String servers;
	
	@Value("${memcached.servers:TEXT}")
	private Protocol protocol;
	
	@Value("${memcached.compressionThreshold:1024}")
	private int compressionThreshold;
	
	@Value("${memcached.opTimeout:30000}")
	private long opTimeout;
	
	@Value("${memcached.timeoutExceptionThreshold:1998}")
	private int timeoutExceptionThreshold;
	
	@Value("${memcached.hashAlg:KETAMA_HASH}")
	private DefaultHashAlgorithm hashAlg;
	
	@Value("${memcached.locatorType:CONSISTENT}")
	private Locator locatorType;
	
	@Value("${memcached.failureMode:Redistribute}")
	private FailureMode failureMode;
	
	@Value("${memcached.useNagleAlgorithm:false}")
	private boolean useNagleAlgorithm;
	
	@Bean
	public SerializingTranscoder transcoder() {
		SerializingTranscoder transcoder = new SerializingTranscoder();
		transcoder.setCompressionThreshold(compressionThreshold);
		return transcoder;
	}

	@Bean
	public MemcachedClientFactoryBean memcachedClient(SerializingTranscoder transcoder) {
		MemcachedClientFactoryBean factoryBean = new MemcachedClientFactoryBean();
		factoryBean.setServers(servers);
		factoryBean.setProtocol(protocol);
		factoryBean.setTranscoder(transcoder);
		factoryBean.setOpTimeout(opTimeout);
		factoryBean.setTimeoutExceptionThreshold(timeoutExceptionThreshold);
		factoryBean.setHashAlg(hashAlg);
		factoryBean.setLocatorType(locatorType);
		factoryBean.setFailureMode(failureMode);
		factoryBean.setUseNagleAlgorithm(useNagleAlgorithm);
		return factoryBean;
	}
}
