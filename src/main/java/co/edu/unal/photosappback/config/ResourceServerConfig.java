package co.edu.unal.photosappback.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**").permitAll();
		http.
                anonymous().disable()
                .authorizeRequests()
                .antMatchers("/user/**").access("hasRole('WEB_CLIENT')")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

}