package ru.ystu.labs_store.security;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;import org.springframework.security.config.annotation.web.builders.HttpSecurity;import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;import org.springframework.security.crypto.password.NoOpPasswordEncoder;import org.springframework.security.crypto.password.PasswordEncoder;import org.springframework.security.web.util.matcher.AntPathRequestMatcher;import ru.ystu.labs_store.service.user.UserService;@Configuration@EnableWebSecuritypublic class SecurityConfig extends WebSecurityConfigurerAdapter {	private final AccessDeniedHandler accessDeniedHandler;	private final UserService userService;	public SecurityConfig(AccessDeniedHandler accessDeniedHandler, UserService userService) {		this.accessDeniedHandler = accessDeniedHandler;		this.userService = userService;	}	@Override	protected void configure(HttpSecurity http) throws Exception {		http.authorizeRequests()				.antMatchers("/css/**").permitAll()				.antMatchers("/add", "/edit/**", "/remove/**").hasRole("admin")				.anyRequest().authenticated()				.and().formLogin().loginPage("/login")				.defaultSuccessUrl("/").permitAll()				.and()				.logout()				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))				.logoutSuccessUrl("/login?logout")				.permitAll()				.and()				.exceptionHandling()				.accessDeniedHandler(accessDeniedHandler);	}	@Override	public void configure(AuthenticationManagerBuilder auth) throws Exception {		auth.userDetailsService(userService);	}	@Bean	public PasswordEncoder getPasswordEncoder() {		return NoOpPasswordEncoder.getInstance();	}}