package com.mstack.stage.service.user.impl;

import com.mstack.stage.UserInfoManager;
import com.mstack.stage.service.user.UserInfoService;
import com.mstack.stage.domain.entity.UserInfoEntity;
import com.mstack.stage.dto.user.UserInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoManager userInfoManager;

    @Override
    public UserInfoDto selectByUserName(String userName) {
        UserInfoEntity userInfoEntity = userInfoManager.selectByUserName(userName);
        if(ObjectUtils.isEmpty(userInfoEntity)){
            return null;
        }
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(userInfoEntity, userInfoDto);
        return userInfoDto;
    }
}
