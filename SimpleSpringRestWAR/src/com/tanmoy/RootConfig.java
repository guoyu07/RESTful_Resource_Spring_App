package com.tanmoy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import login.config.SecurityConfig;

@Import({SecurityConfig.class})
@ImportResource({
    "classpath*:META-INF/applicationContext.xml",
    "classpath*:META-INF/SQLContext.xml"
    
})


@PropertySources({
    @PropertySource("WEB-INF/jdbc.properties"),
    //@PropertySource("classpath:META-INF/jdbc.properties")
})
public class RootConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}


