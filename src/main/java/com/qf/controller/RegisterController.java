package com.qf.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qf.email.EmailService;
import com.qf.utils.TencentCosUtil;
import com.qf.utils.isValidEmail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @auther: sin
 * @Date: 2023/6/13 - 06 - 13 - 21:09
 * @Description: com.qf.controller
 * @version: 1.0
 */
@Api(tags = "注册模块")
@RestController
@Slf4j
public class RegisterController {

    @Resource
    private EmailService emailService;

    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "用户注册")
    @ResponseBody
    @PostMapping(value = "/Register")
    public String Register(@RequestBody JSONObject object) {
        System.out.println(object);
        return "verifyCode";
    }
    @ApiOperation(value = "获取验证码")
    @ResponseBody
    @PostMapping(value = "/getEmailCode")
    public String getEmailCode(@RequestBody String email) {
        if(!isValidEmail.isValidEmail(email)) {
            return "邮箱错误";
        }
        System.out.println(email);
        String verifyCode = (String) redisTemplate.opsForValue().get(email);
        if(verifyCode == null) {
            verifyCode = emailService.getRegisterEmailVerificationCode(email, "RegisterCode");
        } else {
            // 验证码未过期，不需要继续获取
        }
        return verifyCode;
    }
    @ResponseBody
    @PostMapping(value = "/upload")
    public String upload(@RequestBody JSONObject object) throws IOException {

        System.out.println(object);
        List<String> imgList = new ArrayList<>();
        JSONArray base = object.getJSONArray("base");
        for (int i = 0; i < base.size(); i++) {
            Map<String, String> o = (Map<String, String>) base.get(i);
            String url = o.get("url");
            String picType = url.substring(url.indexOf(".") + 1, url.length());
            byte[] decodeBase64 = Base64.decodeBase64(o.get("base64"));
            File file = File.createTempFile(String.valueOf(System.currentTimeMillis()),picType);
            FileUtils.writeByteArrayToFile(file, decodeBase64);
            String picName = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis() + "."+picType;
            String picUrl= TencentCosUtil.uploadfile(file, picName);
            imgList.add(picUrl);
        }
        System.out.println(imgList);
        return "完成";
    }


}
