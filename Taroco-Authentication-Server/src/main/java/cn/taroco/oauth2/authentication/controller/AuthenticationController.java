package cn.taroco.oauth2.authentication.controller;

import cn.taroco.oauth2.authentication.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liuht
 * @date 2018年03月10日
 */
@Controller
@RequestMapping("/oauth")
public class AuthenticationController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    /**
     * 清除 accessToken
     *
     * @param accessToken accessToken
     * @return true/false
     */
    @PostMapping("/removeToken")
    @ResponseBody
    public ResponseEntity<Response> removeToken(String accessToken) {
        return ResponseEntity.ok(Response.success(consumerTokenServices.revokeToken(accessToken)));
    }
}
