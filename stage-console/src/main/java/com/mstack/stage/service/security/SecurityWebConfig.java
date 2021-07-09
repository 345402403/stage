package com.mstack.stage.service.security;

import com.mstack.stage.service.user.UserInfoService;
import com.mstack.stage.dto.user.UserInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.session.MapSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

@EnableWebSecurity
@Configuration
@Slf4j
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserInfoService userInfoService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        UserLoginFilter userLoginFilter = new UserLoginFilter(super.authenticationManager());
        // 登录成功
        userLoginFilter.setAuthenticationSuccessHandler((req, resp, auth) -> {
            resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
            String token = req.getSession().getId();
            resp.getWriter().write("{\"code\": 0, \"token\": \"" + token +"\"}");
        });
        // 登录失败
        userLoginFilter.setAuthenticationFailureHandler((req, resp, auth) -> {
            resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            resp.getWriter().write("{\"code\": 1, \"message\": \""+ auth.getMessage() + "\"}");
        });
        // 不需要鉴权的路径
        http.authorizeRequests().antMatchers("/error", "/captchaImage").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();
        http.logout().logoutUrl("/logout").logoutSuccessHandler( (req,resp, auth) ->{
            resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println("{\"code\":0}");
        });
        http.csrf().disable();
        http.addFilterBefore(userLoginFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
    }
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/500").permitAll()
                .antMatchers("/404").permitAll()
                .antMatchers("/403").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/admin/index").hasRole("ADMIN")//指定权限为ADMIN才能访问
                .antMatchers("/person").hasAnyRole("ADMIN", "USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()//使用表单认证方式
                .loginProcessingUrl("/authentication/form")//配置默认登录入口
                .successHandler(myLoginSuccessHandler)
                .failureHandler(myLoginFailureHandler)
                .and()
                .csrf().disable();
        UserLoginFilter userLoginFilter = new UserLoginFilter(super.authenticationManager());
        http.addFilterBefore(userLoginFilter, UsernamePasswordAuthenticationFilter.class);

    }*/
    @Bean
    public HttpSessionIdResolver sessionIdResolver() {
        return new HeaderHttpSessionIdResolver("X-Token");
    }
    @Bean
    public SessionRepository<MapSession> sessionRepository() {
        return new MapSessionRepository(new ConcurrentHashMap<>());
    }
    /**
     * 自定义认证策略
     *
     * @return
     */
/*    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String password = passwordEncoder().encode("123456");

        log.info("加密后的密码===" + password);

//        auth.inMemoryAuthentication().withUser("admin").password(password)
//                .roles("ADMIN").and();

        auth.inMemoryAuthentication().withUser("user").password(password)
                .roles("USER").and().withUser("admin").password(password)
                .roles("admin").and();

    }*/
    @Autowired
    private AuthenticationSuccessHandler myLoginSuccessHandler; //认证成功结果处理器

    @Autowired
    private AuthenticationFailureHandler myLoginFailureHandler; //认证失败结果处理器

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserInfoDto users = userInfoService.selectByUserName(username);
            if (users == null) {
                throw new UsernameNotFoundException("用户名未找到");
            }
            String password = users.getPassword();
            //PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            String passwordAfterEncoder = passwordEncoder().encode(password);
            System.out.println("密码 =>>>>>" + passwordAfterEncoder);
            return User.withUsername(username).password(passwordAfterEncoder).roles("ADMIN").build();
        };
    }
}
