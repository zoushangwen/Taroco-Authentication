package cn.taroco.oauth2.authentication.vo;

import cn.hutool.core.util.StrUtil;
import cn.taroco.oauth2.authentication.entity.OauthClient;
import lombok.Data;

import java.util.Collection;

/**
 * 客户端vo
 *
 * @author liuht
 * 2019/7/5 15:12
 */
@Data
public class OauthClientVo {

    private String clientId;

    private Collection<String> resourceIds;

    private String clientSecret;

    private Collection<String> scope;

    private Collection<String> authorizedGrantTypes;

    private String webServerRedirectUri;

    private Collection<String> authorities;

    private Boolean autoapprove;

    public OauthClientVo(final OauthClient client) {
        this.setClientId(client.getClientId());
        this.setResourceIds(StrUtil.splitTrim(client.getResourceIds(), StrUtil.COMMA));
        this.setClientSecret(client.getClientSecret());
        this.setScope(StrUtil.splitTrim(client.getScope(), StrUtil.COMMA));
        this.setAuthorizedGrantTypes(StrUtil.splitTrim(client.getAuthorizedGrantTypes(), StrUtil.COMMA));
        this.setWebServerRedirectUri(client.getWebServerRedirectUri());
        this.setAuthorities(StrUtil.splitTrim(client.getAuthorities(), StrUtil.COMMA));
        this.setAutoapprove(Boolean.valueOf(client.getAutoapprove()));
    }
}
