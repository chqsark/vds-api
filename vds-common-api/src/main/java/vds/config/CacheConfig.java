package vds.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
@EnableCaching
public class CacheConfig {
    // TODO: For simplicity, we only use default in memory implementation of cache.
    // It does not support multi-process environment (i.e. an application deployed on several nodes).
    // To supports that, it have connected to external cache server
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Collections.singleton(new ConcurrentMapCache("pages", true)));
        return cacheManager;
    }
}
