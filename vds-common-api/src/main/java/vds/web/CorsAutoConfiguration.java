package vds.web;

import org.apache.catalina.filters.CorsFilter;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.Servlet;

@Configuration
@ConditionalOnWebApplication
@Order(Ordered.HIGHEST_PRECEDENCE + 10)
@AutoConfigureAfter(EmbeddedServletContainerAutoConfiguration.class)
public class CorsAutoConfiguration {
    /**
     * Nested configuration for if Tomcat is being used.
     */
    @Configuration
    @ConditionalOnClass({Servlet.class, CorsFilter.class})
    public static class CorsTomcat {
        @Bean
        public Filter corsFilter() {
            return new CorsFilter();
        }
    }

    /**
     * Nested configuration if Jetty is being used.
     */
    @Configuration
    @ConditionalOnClass({Servlet.class, CrossOriginFilter.class})
    public static class CorsJetty {
        @Bean
        public Filter corsFilter() {
            return new CrossOriginFilter();
        }
    }
}
