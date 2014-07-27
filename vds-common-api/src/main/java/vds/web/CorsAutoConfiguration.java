package vds.web;

import javax.servlet.Filter;
import javax.servlet.Servlet;

import org.apache.catalina.filters.CorsFilter;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
public class CorsAutoConfiguration {
	/**
	 * Nested configuration for if Tomcat is being used.
	 */
	@Configuration
	@ConditionalOnClass({ Servlet.class, Tomcat.class })
	public static class EmbeddedTomcat {
		@Bean
		public Filter corsFilter() {
			return new CorsFilter();
		}
	}
}
