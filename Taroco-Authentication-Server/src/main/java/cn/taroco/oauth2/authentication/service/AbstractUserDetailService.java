package cn.taroco.oauth2.authentication.service;

import cn.taroco.oauth2.authentication.common.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 抽象UserDetailsService 满足不同的登录方式
 *
 * @author liuht
 * 2019/5/14 11:36
 */
@Slf4j
public abstract class AbstractUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserVO(username);
    }

    /**
     * 获取 UserVo 对象
     *
     * @param username 用户名
     * @return
     */
    protected abstract UserVo getUserVO(String username);
}
