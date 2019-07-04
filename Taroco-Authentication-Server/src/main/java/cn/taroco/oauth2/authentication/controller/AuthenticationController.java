package cn.taroco.oauth2.authentication.controller;

import cn.taroco.oauth2.authentication.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liuht
 * @date 2018年03月10日
 */
@Controller
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    /**
     * 认证页面
     *
     * @return ModelAndView
     */
    @GetMapping("/require")
    public String require() {
        return "index";
    }

    /**
     * 清除 accesstoken
     *
     * @param accesstoken accesstoken
     * @return true/false
     */
    @PostMapping("/removeToken")
    @ResponseBody
    public ResponseEntity<Response> removeToken(String accesstoken) {
        return ResponseEntity.ok(Response.success(consumerTokenServices.revokeToken(accesstoken)));
    }
}
