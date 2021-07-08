package com.mstack.stage.dto.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 *
 * @author limeng429
 * @date 2021/7/8 20:00
 */
@Data
public class UserInfoDto implements Serializable {

    private static final long serialVersionUID = 1714594546174344285L;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String portrait;
    /**
     * 手机号
     */
    private String mobilePhone;
    /**
     * 微信号
     */
    private String wechat;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 是否启用
     */
    private Integer isEnabled;
    /**
     * 创建时间
     */
    private Date createTime;
}
