package com.mstack.stage.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author limeng
 */
@Data
public class BaseEntity {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 删除状态默认
     * 0:未删除 1:删除
     */
    private Integer isDelete;

}
