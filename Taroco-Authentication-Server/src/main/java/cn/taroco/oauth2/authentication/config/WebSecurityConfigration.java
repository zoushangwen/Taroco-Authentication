package cn.taroco.oauth2.authentication.config;

import cn.taroco.oauth2.authentication.filter.CustomUsernamePasswordAuthenticationFilter;
import cn.taroco.oauth2.authentication.filter.MobileTokenAuthenticationFilter;
import cn.taroco.oauth2.authentication.filter.SmsCodeAuthenticationFilter;
import cn.taroco.oauth2.authentication.handler.CustomAccessDeniedHandler;
import cn.taroco.oauth2.authentication.handler.CustomExceptionEntryPoint;
import cn.taroco.oauth2.authentication.handler.MobileTokenLoginFailureHandler;
import cn.taroco.oauth2.authentication.handler.MobileTokenLoginSuccessHandler;
import cn.taroco.oauth2.authentication.handler.UsernamePasswordAuthenticationFailureHandler;
import cn.taroco.oauth2.authentication.handler.UsernamePasswordAuthenticationSuccessHandler;
import cn.taroco.oauth2.authentication.handler.UsernamePasswordLogoutSuccessHandler;
import cn.taroco.oauth2.authentication.provider.MobileTokenAuthenticationProvider;
import cn.taroco.oauth2.authentication.provider.SmsCodeAuthenticationProvider;
import cn.taroco.oauth2.authentication.redis.TarocoRedisRepository;
import cn.taroco.oauth2.authentication.service.MobileUserDetailsService;
import cn.taroco.oauth2.authentication.service.UserNameUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * webSecurity 权限控制类
 *
 * @author liuht
 * @date 2018/7/24 15:58
 * @see SecurityFilterAutoConfiguration
 * @see SpringBootWebSecurityConfiguration
 */
@EnableWebSecurity
public class WebSecurityConfigration extends WebSecurityConfigurerAdapter {

    @Autowired
    private TarocoOauth2Properties oauth2Properties;

    @Autowired
    private UserNameUserDetailsServiceImpl userNameUserDetailsService;

    @Autowired
    private MobileUserDetailsService mobileUserDetailsService;

    @Autowired
    private TarocoRedisRepository redisRepository;

    @Autowired
    private MobileTokenLoginFailureHandler mobileTokenLoginFailureHandler;

    @Autowired
    private MobileTokenLoginSuccessHandler mobileTokenLoginSuccessHandler;

    @Autowired
    private UsernamePasswordAuthenticationSuccessHandler successHandler;

    @Autowired
    private UsernamePasswordAuthenticationFailureHandler failureHandler;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private CustomExceptionEntryPoint exceptionEntryPoint;

    @Autowired
    private UsernamePasswordLogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http
                        .addFilterAfter(mobileTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                        .addFilterAfter(smsCodeAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                        .addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                        .formLogin().loginPage("/").permitAll()
                        .loginProcessingUrl("/login").permitAll()
                        .and().logout().logoutUrl("/logout").permitAll().logoutSuccessHandler(logoutSuccessHandler)
                        // 异常处理filter: ExceptionTranslationFilter
                        .and().exceptionHandling()
                        // 匿名用户访问无权限资源时的异常
                        //.authenticationEntryPoint(exceptionEntryPoint)
                        // 认证过的用户访问无权限资源时的异常
                        .accessDeniedHandler(accessDeniedHandler)
                        .and().authorizeRequests();

        final List<String> urlPermitAll = oauth2Properties.getUrlPermitAll();
        urlPermitAll.forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().authenticated().and().cors().and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 默认的用户名密码认证器
                .authenticationProvider(daoAuthenticationProvider())
                // 手机号获取token认证器
                .authenticationProvider(mobileTokenAuthenticationProvider())
                // 手机号验证码登录认证器
                .authenticationProvider(smsCodeAuthenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    /**
     * 加密器 spring boot 2.x没有默认的加密器了
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 注册自定义的UsernamePasswordAuthenticationFilter
     *
     * @throws Exception
     */
    @Bean
    public CustomUsernamePasswordAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    /**
     * 默认的用户名密码 AuthenticationProvider
     *
     * @return
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userNameUserDetailsService);
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * 手机号获取token过滤器
     */
    @Bean
    public MobileTokenAuthenticationFilter mobileTokenAuthenticationFilter() throws Exception {
        final MobileTokenAuthenticationFilter filter = new MobileTokenAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(mobileTokenLoginSuccessHandler);
        filter.setAuthenticationFailureHandler(mobileTokenLoginFailureHandler);
        return filter;
    }

    /**
     * 手机号获取token认证逻辑
     */
    @Bean
    public MobileTokenAuthenticationProvider mobileTokenAuthenticationProvider() {
        final MobileTokenAuthenticationProvider provider = new MobileTokenAuthenticationProvider();
        provider.setRedisRepository(redisRepository);
        provider.setUserDetailsService(mobileUserDetailsService);
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    /**
     * 手机号验证码登录filter
     */
    @Bean
    public SmsCodeAuthenticationFilter smsCodeAuthenticationFilter() throws Exception {
        final SmsCodeAuthenticationFilter filter = new SmsCodeAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        return filter;
    }

    /**
     * 手机号验证码登录认证逻辑
     */
    @Bean
    public SmsCodeAuthenticationProvider smsCodeAuthenticationProvider() {
        final SmsCodeAuthenticationProvider provider = new SmsCodeAuthenticationProvider();
        provider.setRedisRepository(redisRepository);
        provider.setUserDetailsService(mobileUserDetailsService);
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }
}
