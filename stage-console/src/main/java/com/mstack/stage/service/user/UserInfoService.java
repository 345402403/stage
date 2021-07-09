package com.mstack.stage.service.user;

import com.mstack.stage.dto.user.UserInfoDto;

public interface UserInfoService {

    UserInfoDto selectByUserName(String userName);
}
