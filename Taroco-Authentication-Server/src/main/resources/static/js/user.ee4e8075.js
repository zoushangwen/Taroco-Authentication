(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["user"],{6681:function(e,t,a){},ac2a:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"main"},[a("a-form",{ref:"formLogin",staticClass:"user-layout-login",attrs:{id:"formLogin",form:e.form},on:{submit:e.handleSubmit}},[a("a-tabs",{attrs:{activeKey:e.customActiveKey,tabBarStyle:{textAlign:"center",borderBottom:"unset"}},on:{change:e.handleTabClick}},[a("a-tab-pane",{key:"tab1",attrs:{tab:"账号密码登录"}},[a("a-form-item",[a("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["username",{rules:[{required:!0,message:"请输入帐户名或邮箱地址"},{validator:e.handleUsernameOrEmail}],validateTrigger:"change"}],expression:"[\n              'username',\n              {rules: [{ required: true, message: '请输入帐户名或邮箱地址' }, { validator: handleUsernameOrEmail }], validateTrigger: 'change'}\n            ]"}],attrs:{size:"large",type:"text",placeholder:"账户: admin"}},[a("a-icon",{style:{color:"rgba(0,0,0,.25)"},attrs:{slot:"prefix",type:"user"},slot:"prefix"})],1)],1),a("a-form-item",[a("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["password",{rules:[{required:!0,message:"请输入密码"}],validateTrigger:"blur"}],expression:"[\n              'password',\n              {rules: [{ required: true, message: '请输入密码' }], validateTrigger: 'blur'}\n            ]"}],attrs:{size:"large",type:"password",autocomplete:"false",placeholder:"密码: admin"}},[a("a-icon",{style:{color:"rgba(0,0,0,.25)"},attrs:{slot:"prefix",type:"lock"},slot:"prefix"})],1)],1)],1),a("a-tab-pane",{key:"tab2",attrs:{tab:"手机号登录"}},[a("a-form-item",[a("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["mobile",{rules:[{required:!0,pattern:/^1[34578]\d{9}$/,message:"请输入正确的手机号"}],validateTrigger:"change"}],expression:"['mobile', {rules: [{ required: true, pattern: /^1[34578]\\d{9}$/, message: '请输入正确的手机号' }], validateTrigger: 'change'}]"}],attrs:{size:"large",type:"text",placeholder:"手机号"}},[a("a-icon",{style:{color:"rgba(0,0,0,.25)"},attrs:{slot:"prefix",type:"mobile"},slot:"prefix"})],1)],1),a("a-row",{attrs:{gutter:16}},[a("a-col",{staticClass:"gutter-row",attrs:{span:16}},[a("a-form-item",[a("a-input",{directives:[{name:"decorator",rawName:"v-decorator",value:["captcha",{rules:[{required:!0,message:"请输入验证码"}],validateTrigger:"blur"}],expression:"['captcha', {rules: [{ required: true, message: '请输入验证码' }], validateTrigger: 'blur'}]"}],attrs:{size:"large",type:"text",placeholder:"验证码"}},[a("a-icon",{style:{color:"rgba(0,0,0,.25)"},attrs:{slot:"prefix",type:"mail"},slot:"prefix"})],1)],1)],1),a("a-col",{staticClass:"gutter-row",attrs:{span:8}},[a("a-button",{staticClass:"getCaptcha",attrs:{tabindex:"-1",disabled:e.state.smsSendBtn},domProps:{textContent:e._s(e.state.smsSendBtn?e.state.time+" s":"获取验证码")},on:{click:function(t){return t.stopPropagation(),t.preventDefault(),e.getCaptcha(t)}}})],1)],1)],1)],1),a("a-form-item",{staticStyle:{"margin-top":"24px"}},[a("a-button",{staticClass:"login-button",attrs:{size:"large",type:"primary",htmlType:"submit",loading:e.state.loginBtn,disabled:e.state.loginBtn}},[e._v("确定")])],1)],1)],1)},s=[],i=a("cebc"),n=a("2f62"),o=a("ca00"),l={components:{},data:function(){return{customActiveKey:"tab1",loginBtn:!1,loginType:0,stepCaptchaVisible:!1,form:this.$form.createForm(this),state:{time:60,loginBtn:!1,loginType:0,smsSendBtn:!1}}},created:function(){},methods:Object(i["a"])({},Object(n["b"])(["Login","Logout"]),{handleUsernameOrEmail:function(e,t,a){var r=this.state,s=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;s.test(t)?r.loginType=0:r.loginType=1,a()},handleTabClick:function(e){this.customActiveKey=e},handleSubmit:function(e){var t=this;e.preventDefault();var a=this.form.validateFields,r=this.state,s=this.customActiveKey,n=this.Login;r.loginBtn=!0;var o="tab1"===s?["username","password"]:["mobile","captcha"];a(o,{force:!0},function(e,a){if(e)setTimeout(function(){r.loginBtn=!1},600);else{var s=Object(i["a"])({},a);delete s.username,s[r.loginType?"username":"email"]=a.username,s.password=a.password,n(s).then(function(e){return t.loginSuccess(e)}).finally(function(){r.loginBtn=!1})}})},getCaptcha:function(e){e.preventDefault();var t=this.form.validateFields,a=this.state;t(["mobile"],{force:!0},function(e,t){if(!e){a.smsSendBtn=!0;var r=window.setInterval(function(){a.time--<=0&&(a.time=60,a.smsSendBtn=!1,window.clearInterval(r))},1e3)}})},stepCaptchaSuccess:function(){this.loginSuccess()},stepCaptchaCancel:function(){var e=this;this.Logout().then(function(){e.loginBtn=!1,e.stepCaptchaVisible=!1})},loginSuccess:function(e){var t=this;this.$router.push({name:"home"}),setTimeout(function(){t.$notification.success({message:"欢迎",description:"".concat(Object(o["a"])(),"，欢迎回来")})},1e3)}})},c=l,u=(a("d46a"),a("2877")),m=Object(u["a"])(c,r,s,!1,null,"fa58d020",null);t["default"]=m.exports},d46a:function(e,t,a){"use strict";var r=a("6681"),s=a.n(r);s.a}}]);