package com.ozq.spzx.manager.service;

import com.ozq.spzx.model.dto.system.LoginDto;
import com.ozq.spzx.model.entity.system.SysUser;
import com.ozq.spzx.model.vo.system.LoginVo;

/**
 * @Author ozq
 * @Date 2023/11/5 20:57
 * @Description
 */
public interface SysUserService {
    LoginVo login(LoginDto loginDto);
}
