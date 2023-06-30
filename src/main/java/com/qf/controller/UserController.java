package com.qf.controller;

import cn.dev33.satoken.util.SaResult;
import com.qf.pojo.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author sin
 * @Date: 2023/6/2 - 06 - 02 - 15:30
 * @Description: com.qf.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/logi")
    public SaResult Login(@Valid @RequestBody User user) {
        System.out.println(user);
        System.out.println(user.getUsername());
        return new SaResult(200, "登录成功", null);
    }
    //    @RequestMapping("/add")
//    @ResponseBody
//    public String addUser() {
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime localDateTime = LocalDateTime.now();
//        String create_time = localDateTime.format(df);
//        LocalDateTime prettyTime = LocalDateTime.parse(create_time,df);
//        User user = new User(null, "2", "2", "2", "2", "2", null, 0);
//        userService.add(null, "1", "1", "1", "1", "1", prettyTime, 0);
////        userService.insert(user);
//        return  "添加成功";
//    }
}
