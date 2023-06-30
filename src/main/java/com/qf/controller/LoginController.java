package com.qf.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @author sin
 * @Date: 2023/6/2 - 06 - 02 - 15:36
 * @Description: com.qf.controller
 * @version: 1.0
 */
@Api(tags = "登录模块")
@RestController
public class LoginController {

    @Resource
    private PasswordEncoder passwordEncoder;

    @ApiOperation(value = "用户登录", notes = "wx.login上传加密字符串即可")
    @PostMapping("/login")
    public SaResult wxLogin(@RequestBody String code) {
        System.out.println(code);
        StpUtil.login(code);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return new SaResult(SaResult.CODE_SUCCESS, "登录成功", tokenInfo);
    }
    @DeleteMapping ("/test-del/{id}/{name}")
    public String del(@PathVariable String id, @PathVariable String name) {
        System.out.println("输出数据：" + id + "===" + name);
        return "测试成功";
    }

    @GetMapping("/test/{id}")
    public String test(@PathVariable String id) {
        System.out.println(id);
        return "/jd/static/index";
    }
    /*
    @ApiOperation(value = "用户登录", notes = "wx.login上传加密字符串即可")
    @ResponseBody
    @PostMapping(value = "/Login")
    public SaResult Login(String code) {
        String wxspAppid = "wx80a51d9fbcf1ed97";
        String wxspSecret = "ee5e4cf20fd52eed0bd8cc82fe9917d1";
        String grant_type = "authorization_code";
        System.out.println(code);
        //获取openid
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        String str = HttpMethodUtil.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        JSONObject json = JSONObject.parseObject(str);
        String openid = json.get("openid").toString();
        String newOpenId = passwordEncoder.encode(openid);
        System.out.println(openid);
//        https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx80a51d9fbcf1ed97&secret=ee5e4cf20fd52eed0bd8cc82fe9917d1
//        String params1 = "appid="+wxspAppid + "&secret=" + wxspSecret + "&grant_type=client_credential";
//        String str1= HttpMethodUtil.sendGet("https://api.weixin.qq.com/cgi-bin/token ",params1);
//        String params1 = "appid="+wxspAppid + "&secret=" + wxspSecret + "&grant_type=client_credential";
//        String str1= HttpMethodUtil.sendGet("https://api.weixin.qq.com/cgi-bin/token ",params1);
//        JSONObject json1=JSONObject.parseObject(str1);
//        System.out.println(json1);
//        String access_token=json1.get("access_token").toString();
//        System.out.println(access_token);
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime localDateTime = LocalDateTime.now();
//        String create_time = localDateTime.format(df);
//        LocalDateTime prettyTime = LocalDateTime.parse(create_time, df);
        User user = new User(null, newOpenId, "2", "2", "2", "2", null, 0);
        User resUser  = userService.selectOneByPhone(user.getPhone());
        StpUtil.login(openid);

        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return new SaResult(SaResult.CODE_SUCCESS, "登录成功", tokenInfo);
    }
     */

}
