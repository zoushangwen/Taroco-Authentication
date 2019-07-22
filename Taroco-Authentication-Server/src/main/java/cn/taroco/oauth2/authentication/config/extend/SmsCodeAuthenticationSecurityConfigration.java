package cn.taroco.oauth2.authentication.config.extend;

import cn.taroco.oauth2.authentication.filter.SmsCodeAuthenticationFilter;
import cn.taroco.oauth2.authentication.handler.UsernamePasswordAuthenticationFailureHandler;
import cn.taroco.oauth2.authentication.handler.UsernamePasswordAuthenticationSuccessHandler;
import cn.taroco.oauth2.authentication.provider.SmsCodeAuthenticationProvider;
import cn.taroco.oauth2.authentication.redis.TarocoRedisRepository;
import cn.taroco.oauth2.authentication.service.MobileUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 手机号/验证码登录 安全配置
 *
 * @author liuht
 * 2019/7/22 16:14
 */
@Component
public class SmsCodeAuthenticationSecurityConfigration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private TarocoRedisRepository redisRepository;

    @Autowired
    private MobileUserDetailsService mobileUserDetailsService;

    @Autowired
    private UsernamePasswordAuthenticationSuccessHandler successHandler;

    @Autowired
    private UsernamePasswordAuthenticationFailureHandler failureHandler;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        final SmsCodeAuthenticationFilter filter = new SmsCodeAuthenticationFilter();
        filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);

        final SmsCodeAuthenticationProvider provider = new SmsCodeAuthenticationProvider();
        provider.setRedisRepository(redisRepository);
        provider.setUserDetailsService(mobileUserDetailsService);
        provider.setHideUserNotFoundExceptions(false);

        http
                .authenticationProvider(provider)
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
