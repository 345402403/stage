package com.mstack.stage;

import com.mstack.stage.dao.common.UserInfoDao;
import com.mstack.stage.domain.entity.UserInfoEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Service
public class UserInfoManager {
    @Resource
    private UserInfoDao userInfoDao;

    @Transactional(readOnly = true)
    public UserInfoEntity selectByUserName(String userName) {
        Assert.notNull(userName, "用户名不能为空");
        return userInfoDao.selectByUserName(userName);
    }

}
