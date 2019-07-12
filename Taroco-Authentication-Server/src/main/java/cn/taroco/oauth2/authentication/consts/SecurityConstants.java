package cn.taroco.oauth2.authentication.consts;

/**
 * Security 权限常量
 *
 * @author liuht
 */
public interface SecurityConstants {

    /**
     * basic
     */
    String BASIC_HEADER = "Basic ";

    /**
     * AUTHORIZATION name
     */
    String AUTHORIZATION = "Authorization";

    /**
     * 用户名信息头
     */
    String USER_NAME_HEADER = "x-user-name";

    /**
     * 用户密码信息头
     */
    String USER_ID_HEADER = "x-user-id";

    /**
     * 角色信息头
     */
    String USER_ROLE_HEADER = "x-user-role";

    /**
     * license key
     */
    String LICENSE_KEY = "license";

    /**
     * 项目的license
     */
    String LICENSE = "made by taroco";

    /**
     * 手机号登录路径
     */
    String MOBILE_TOKEN_URL = "/oauth/mobile";

    /**
     * sys_oauth_client_details 表的字段，不包括client_id、client_secret
     */
    String CLIENT_FIELDS = "client_id, client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    /**
     * JdbcClientDetailsService 查询语句
     */
    String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS
            + " from oauth_client_details";

    /**
     * 默认的查询语句
     */
    String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    /**
     * 按条件client_id 查询
     */
    String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

}
