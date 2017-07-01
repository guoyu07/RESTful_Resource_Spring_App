package login.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
/*@ImportResource({
    "classpath*:META-INF/applicationContext.xml",
    "classpath*:META-INF/SQLContext.xml"
    
}) Make it better*/

public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	/*@Qualifier("datasourceForAuth")
	@Autowired(required=true)
	DataSource dataSource;*/
	
	private static String REALM="MY_TEST_REALM";
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	  /*auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
	  auth.inMemoryAuthentication().withUser("tanmoy").password("123456").roles("DBA");*/
		
		//authentication against database tables
		auth.jdbcAuthentication().dataSource(dataSource())
		.usersByUsernameQuery(
				"select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
				"select username, role from user_roles where username=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*http.authorizeRequests().
		antMatchers("/secure/**").access("hasRole('ROLE_ADMIN')").
		antMatchers("/registerme").permitAll().
		antMatchers("/register").permitAll().
		antMatchers("/registered").permitAll().
		anyRequest().authenticated().
		and().formLogin().  //login configuration
                loginPage("/customLogin.jsp").
                loginProcessingUrl("/appLogin").
                usernameParameter("app_username").
                passwordParameter("app_password").
                defaultSuccessUrl("/secure/home").	
		and().logout().    //logout configuration
		logoutUrl("/appLogout"). 
		logoutSuccessUrl("/customLogin.jsp?logout");*/
		
		http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/restful/**").hasRole("ADMIN")
        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	@Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }
	
	@Value("${jdbcForAuth.url}")
	private String url;
	@Value("${jdbc.username}")
	private String userid;
	@Value("${jdbc.password}")
	private String passwod;
	@Value("${jdbc.driverclassname}")
	private String driverclassname;
	
	@Bean(name = "datasourceForAuth")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(driverclassname);
		driverManagerDataSource.setUrl(url);
		driverManagerDataSource.setUsername(userid);
		driverManagerDataSource.setPassword(passwod);
		return driverManagerDataSource;
	}
	
	/* To allow Pre-flight [OPTIONS] request from browser */
   /* @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }*/
}
