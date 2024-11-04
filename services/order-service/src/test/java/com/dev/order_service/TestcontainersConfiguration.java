package com.dev.order_service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	static final MySQLContainer<?> mysqlContainer =
			new MySQLContainer<>(DockerImageName.parse("mysql:latest"));

	static {
		mysqlContainer.start();
	}

	@Bean
	@ServiceConnection
	MySQLContainer<?> mysqlContainer() {
		return mysqlContainer;
	}

}
