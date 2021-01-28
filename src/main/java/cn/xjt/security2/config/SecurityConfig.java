package cn.xjt.security2.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 内容：
 *
 * @author
 * @date 2020/10/8-12:25
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	//授权
	@Override
	protected void configure(HttpSecurity http) throws Exception {

//		所有人可以访问首页，功能页只有对相应权限得人才能访问
//		请求权限得规则
		http.authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/level1/**").hasAnyRole("vip1")
				.antMatchers("/level2/**").hasAnyRole("vip2")
				.antMatchers("/level3/**").hasAnyRole("vip3");

//		没有权限，会跳到登录页面
		http.formLogin().loginPage("/tologin")
				.loginProcessingUrl("/login").usernameParameter("user").passwordParameter("pwd");

//		开启注销功能
		http.logout().logoutSuccessUrl("/");

//		防止网络攻击
		http.csrf().disable();          //关闭安全

//		开启记住我功能
		http.rememberMe().rememberMeParameter("rememberme");
	}

//	认证

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
				.withUser("xujiangtao")
				.password(new BCryptPasswordEncoder().encode("123456"))
				.roles("vip1")
				.and().withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3");
	}
}
