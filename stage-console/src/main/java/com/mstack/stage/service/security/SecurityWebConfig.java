package com.mstack.stage.service.security;

import com.mstack.stage.service.user.UserInfoService;
import com.mstack.stage.dto.user.UserInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@EnableWebSecurity
@Configuration
@Slf4j
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserInfoService userInfoService;

    @Override
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
                // .successHandler(myLoginSuccessHandler)
                //.failureHandler(myLoginFailureHandler)
                .and()
                .csrf().disable();
    }

    /**
     * 自定义认证策略
     *
     * @return
     */
/*    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String password = passwordEncoder().encode("1234567");

        log.info("加密后的密码===" + password);

//        auth.inMemoryAuthentication().withUser("admin").password(password)
//                .roles("ADMIN").and();

        auth.inMemoryAuthentication().withUser("user").password(password)
                .roles("USER").and().withUser("admin").password(password)
                .roles("admin").and();

    }*/
  /*  @Autowired
    private AuthenticationSuccessHandler myLoginSuccessHandler; //认证成功结果处理器

    @Autowired
    private AuthenticationFailureHandler myLoginFailureHandler; //认证失败结果处理器*/

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
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            String passwordAfterEncoder = passwordEncoder.encode(password);
            return User.withUsername(username).password(passwordAfterEncoder).roles("").build();
        };
    }
}
