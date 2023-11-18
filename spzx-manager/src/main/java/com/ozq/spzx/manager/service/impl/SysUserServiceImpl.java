package com.ozq.spzx.manager.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSONObject;
import com.ozq.spzx.common.exception.BusinessException;
import com.ozq.spzx.manager.mapper.SysUserMapper;
import com.ozq.spzx.manager.service.SysUserService;
import com.ozq.spzx.model.dto.system.LoginDto;
import com.ozq.spzx.model.entity.system.SysUser;
import com.ozq.spzx.model.enums.LoginEnum;
import com.ozq.spzx.model.vo.common.ResultCodeEnum;
import com.ozq.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author ozq
 * @Date 2023/11/5 20:58
 * @Description
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public LoginVo login(LoginDto loginDto) {
        // 获取用户
        String userName = loginDto.getUserName();
        SysUser sysUser = sysUserMapper.queryByUserName(userName);
        // 查询是否有该用户
        if( sysUser == null){
            throw new BusinessException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 校验密码是否正确，md加密,和取出的密码进行比较
        String dataBase_UserName = DigestUtil.md5Hex(loginDto.getPassword());
        String input_password = sysUser.getPassword();
        if(Objects.equals(dataBase_UserName,input_password)){
            throw new BusinessException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 密码一致，生成token,jwt生成token
        String token = UUID.randomUUID().toString().replace("-","");
        // 将token放入redis
        String key = LoginEnum.USER_LOGIN.getValue() + ":" + token;
        stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(sysUser), 30,TimeUnit.MINUTES);
        // 将token返回前端
        LoginVo loginVo = new LoginVo();
        loginVo.setRefresh_token(token);
        return loginVo;
    }
}
