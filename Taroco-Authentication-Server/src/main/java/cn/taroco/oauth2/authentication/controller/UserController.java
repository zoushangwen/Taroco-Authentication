package cn.taroco.oauth2.authentication.controller;

import cn.taroco.oauth2.authentication.common.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述
 *
 * @author liuht
 * 2019/7/4 15:44
 */
@Controller
public class UserController {

    /**
     * 查询登录用户
     *
     * @param authentication 信息
     * @return 用户信息
     */
    @RequestMapping("/user")
    @ResponseBody
    public ResponseEntity<Response> user(Authentication authentication) {
        if (authentication == null) {
            return null;
        }
        return ResponseEntity.ok(Response.success(authentication.getPrincipal()));
    }

    /**
     * Session 失效异步返回
     *
     * @return
     */
    @RequestMapping("/sessionInvalid")
    @ResponseBody
    public ResponseEntity<Response> sessionInvalid() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Response.failure("登录已失效,请重新登录"));
    }
}
