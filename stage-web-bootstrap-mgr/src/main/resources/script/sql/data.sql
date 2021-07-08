-- 预置管理员用户
INSERT INTO user_info ( id, user_name, password, nickName, portrait, mobile_phone, wechat, email, is_enabled, create_user, create_time, update_user, update_time, is_delete, version ) VALUES ( NULL, 'admin', '123456', '管理员', '', '', '', '', 1, 'sys', now() , '', now(), 0, 0 );

