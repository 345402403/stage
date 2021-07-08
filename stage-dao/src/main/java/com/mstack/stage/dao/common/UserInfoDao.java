package com.mstack.stage.dao.common;

import com.mstack.stage.domain.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Param;

public interface UserInfoDao {

    UserInfoEntity selectByUserName(@Param("") String userName);
}
