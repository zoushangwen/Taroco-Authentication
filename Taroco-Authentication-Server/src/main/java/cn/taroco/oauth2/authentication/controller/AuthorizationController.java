package cn.taroco.oauth2.authentication.controller;

import cn.taroco.oauth2.authentication.core.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.endpoint.WhitelabelApprovalEndpoint;
import org.springframework.security.oauth2.provider.endpoint.WhitelabelErrorEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

/**
 * 自定义授权端点
 *
 * @author liuht
 * 2019/5/17 9:56
 * @see WhitelabelApprovalEndpoint
 * @see WhitelabelErrorEndpoint
 * @see AuthorizationEndpoint
 */
@Controller
@SessionAttributes("authorizationRequest")
public class AuthorizationController {
    /**
     * 授权页面 重写{@link WhitelabelApprovalEndpoint}
     *
     * @param model
     * @return
     */
    @RequestMapping("/oauth/confirm_access")
    public String authorizePage(Map<String, Object> model, HttpServletRequest request) {
        // 获取用户名
        String userName = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        model.put("userName", userName);
        @SuppressWarnings("unchecked")
        Map<String, String> scopes = (Map<String, String>) (model.containsKey("scopes") ? model.get("scopes") : request
                .getAttribute("scopes"));
        model.put("scopes", new ArrayList<>(scopes.keySet()));
        return "/confirm_access";
    }

    /**
     * 自定义错误处理 重写{@link WhitelabelErrorEndpoint}
     *
     * @param request
     * @return
     */
    @RequestMapping("/oauth/error")
    @ResponseBody
    public ResponseEntity<Response> handleError(HttpServletRequest request) {
        Object error = request.getAttribute("error");
        String errorSummary;
        if (error instanceof OAuth2Exception) {
            OAuth2Exception oauthError = (OAuth2Exception) error;
            errorSummary = oauthError.getMessage();
        } else {
            errorSummary = "Unknown error";
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.failure(errorSummary));
    }
}
