package cn.taroco.oauth2.authentication.vo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * 用户Vo
 *
 * @author liuht
 * 2019/7/3 9:40
 */
@Data
public class UserVo implements Serializable, UserDetails {

    private static final long serialVersionUID = 8741046663436494432L;

    /**
     * 主键ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否过期
     */
    private Boolean expired;

    /**
     * 是否锁定
     */
    private Boolean locked;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 密码是否过期
     */
    private Boolean passwordExpired;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !passwordExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
