/*
 * package net.guides.springboot2.springboot2jpacrudexample.spring.api.config;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.method.configuration.
 * EnableGlobalMethodSecurity; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.crypto.password.NoOpPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * 
 * @EnableWebSecurity
 * 
 * @Configuration
 * 
 * @EnableGlobalMethodSecurity public class SpringSecurityConfig extends
 * WebSecurityConfigurerAdapter{
 * 
 * @Autowired private UserDetailsService userDetailsService;
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { auth.inMemoryAuthentication() .withUser("admin")
 * .password("admin") .roles("ADMIN") .and() .withUser("user") .password("user")
 * .roles("USER") .and() .withUser("staff") .password("staff") .roles("STAFF");
 * }
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * 
 * http.authorizeRequests() .antMatchers("/api/v1/leaves/**").hasRole("ADMIN")
 * .antMatchers("/api/v1/employees/**").hasRole("USER")
 * .antMatchers("/api/v1/salaries/**").hasRole("STAFF")
 * .antMatchers("/").permitAll() .and().formLogin();
 * 
 * 
 * }
 * 
 * // Non encrypted password is allowed
 * 
 * @Bean public static PasswordEncoder getPasswordEncoder() { return
 * (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance(); }
 * 
 * 
 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
 * throws Exception {
 * auth.userDetailsService(userDetailsService).passwordEncoder(
 * bCryptPasswordEncoder()); }
 * 
 * 
 * @Bean public BCryptPasswordEncoder bCryptPasswordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * }
 */