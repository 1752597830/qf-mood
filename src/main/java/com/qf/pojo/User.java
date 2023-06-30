package com.qf.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author sin
 * @Date: 2023/6/2 - 06 - 02 - 15:29
 * @Description: com.qf.pojo
 * @version: 1.0
 */
@Data
@ApiModel(value = "User对象", description = "")
public class User {
    @NotBlank(message = "用户名不为空")
    private String username;
    private String pwd;
    private List<String> list;
}
