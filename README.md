# Taroco-Authentication 统一认证服务

[![Total lines](https://tokei.rs/b1/github/liuht777/Taroco-Authentication?category=lines)](https://github.com/liuht777/Taroco-Authentication)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/fc4c61a52a8e4c7d9f68db2840b9b446)](https://app.codacy.com/app/liuht777/Taroco-Authentication?utm_source=github.com&utm_medium=referral&utm_content=liuht777/Taroco-Authentication&utm_campaign=Badge_Grade_Dashboard)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/liuht777/Taroco-Authentication)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?label=license)](https://github.com/liuht777/Taroco/blob/master/LICENSE)

- [在线文档](https://www.yuque.com/liuht777/dpilpu)

基于 Spring Security Oauth2 的统一认证服务，采用 [Ant Design Pro of Vue](https://pro.loacg.com/docs/getting-started "Ant Design Pro of Vue") 作为前端界面，重写了 Spring Security 登录模式，支持异步登录，所有接口以及授权端点都支持异步的方式。

![登陆页面](docs/imgs/login.png "登陆页面")

![应用页面](docs/imgs/app.png "应用页面")

## 支持特性

- 支持异步 JSON 登录
- 支持手机号、验证码登录
- 支持 SSO
- 支持 JWT Token
- 支持 Redis Token
- 支持集群部署（已集成 Spring Session）
- 支持 OAuth2.0 定义的四种授权码模式以及刷新 token
- 支持通过手机号和验证码获取 token（类似 password模式）
- 集成了应用管理的功能，方便应用接入
- 完整的 Demo 示例，包括 SSO、Resource Server，以及在 Resource Server 中解析token，获取用户的权限以及认证中添加的额外信息

## Postman 接口调试

前端界面已经集成到 Spring Boot，只需要启动一个后端服务即可。`docs/Taroco Authentication.postman_collection.json` 已导出，请自行导入到 Postman 进行接口调试。

## 友情链接

- [spring-security-oauth 开发者指南](http://projects.spring.io/spring-security-oauth/docs/oauth2.html)
- [Spring Boot & Spring Session](https://docs.spring.io/spring-session/docs/current/reference/html5/guides/boot-redis.html)
- [理解 OAuth 2.0（阮一峰）](http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html)
- [Spring Security 实现原理与源码解析](http://www.iocoder.cn/Spring-Security/good-collection/)
- [OAuth2.0 最简向导](docs/OAuth2.0最简向导.pdf)
- [vue.ant.design](https://vue.ant.design/docs/vue/introduce-cn/)
- [Ant Design Pro of Vue](https://pro.loacg.com/docs/getting-started)
