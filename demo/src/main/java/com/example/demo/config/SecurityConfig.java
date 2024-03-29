package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * SecurityConfig class. write about some class documentation.
 *
 * @author Faruk Hossain
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private DemoAccessDeniedHandler accessDeniedHandler;
	
//	 @Autowired
//	 private AuthenticationManager authenticationManager;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/", "/js/**", "/css/**", "/i18n/**", "/img/**", "/webjars/**", "/registration",
						"/registrationSubmit", "/loginSubmit", "/api/**", "/resources/**", "/product-delete",
						"/product-update", "/get-products", "/send", "/add-promotion", "/update-promotion")
				.permitAll()
				.antMatchers("/adminDashboard", "/questionnaireMapping", "/add-question", "/product", "/productSave",
						"/saveCategory", "/showRole", "/changeUserPassword", "/products", "/promotion-dashboard",
						"/delete-question", "/notifications", "/notifications/**", "/new-notifications")
				.hasRole("ADMIN").anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and()
				.logout().invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
				.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}