package cn.taroco.oauth2.authentication.service;

import cn.taroco.oauth2.authentication.common.UserService;
import cn.taroco.oauth2.authentication.common.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Service;

/**
 *
 * @author liuht
 * 2019/5/14 13:56
 */
@Service
public class MobileUserDetailsService extends AbstractUserDetailService {

    @Autowired
    private UserService userService;

    @Override
    protected UserVo getUserVO(final String username) {
        final UserVo userVO = userService.findUserByMobile(username);
        if (userVO == null) {
            throw new InternalAuthenticationServiceException("手机号: " + username + ", 不存在");
        }
        return userVO;
    }
}
