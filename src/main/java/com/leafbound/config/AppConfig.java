package com.leafbound.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = { "com.leafbound.repositories", "com.leafbound.services", "com.leafbound.aspects" })
@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {

}
