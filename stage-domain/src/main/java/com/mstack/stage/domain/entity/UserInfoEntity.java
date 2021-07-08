package com.mstack.stage.domain.entity;

import com.mstack.stage.domain.BaseEntity;
import lombok.Data;

/**
 * 用户信息
 * @author limeng429
 * @date 2021/7/8 20:00
 */
@Data
public class UserInfoEntity extends BaseEntity {

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
}
