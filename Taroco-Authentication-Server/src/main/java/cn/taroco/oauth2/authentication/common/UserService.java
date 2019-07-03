package cn.taroco.oauth2.authentication.common;

import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 模拟 UserService 实现查询用户
 * 在实际使用上需要替换
 *
 * @author liuht
 * 2019/7/3 9:52
 */
@Service
public class UserService {

    @Autowired
    private PasswordEncoder encoder;

    /**
     * 根据用户名称返回用户
     *
     * @param username 用户名称,必须唯一
     * @return
     */
    public UserVo findUserByUsername(String username) {
        final UserVo userVo = new UserVo();
        userVo.setUsername(username);
        // 密码和用户名保持一致
        userVo.setPassword(encoder.encode(username));
        userVo.setEnabled(true);
        userVo.setUserId(RandomUtil.randomInt());
        userVo.setEnabled(true);
        userVo.setExpired(false);
        userVo.setLocked(false);
        userVo.setPasswordExpired(false);
        return userVo;
    }

    /**
     * 根据手机号返回用户
     *
     * @param mobile 手机号,必须唯一
     * @return
     */
    public UserVo findUserByMobile(String mobile) {
        final UserVo userVo = new UserVo();
        userVo.setUsername(mobile);
        // 密码和用户名保持一致
        userVo.setPassword(encoder.encode(mobile));
        userVo.setEnabled(true);
        userVo.setUserId(RandomUtil.randomInt());
        userVo.setEnabled(true);
        userVo.setExpired(false);
        userVo.setLocked(false);
        userVo.setPasswordExpired(false);
        return userVo;
    }
}
