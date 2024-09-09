package com.nisanth.library.config;

import jakarta.annotation.PostConstruct;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfinispanCacheConfig {

    private final EmbeddedCacheManager cacheManager;

    public InfinispanCacheConfig(EmbeddedCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @PostConstruct
    public void configureCaches() {
        cacheManager.defineConfiguration("books", new ConfigurationBuilder()
                .clustering()
                .cacheMode(CacheMode.LOCAL) // Adjust CacheMode as per your requirement
                .build());
    }
}
