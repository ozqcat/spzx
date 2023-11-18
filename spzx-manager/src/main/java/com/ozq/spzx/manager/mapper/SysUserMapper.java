package com.ozq.spzx.manager.mapper;

import com.ozq.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ozq
 * @Date 2023/11/5 21:01
 * @Description
 */
@Mapper
public interface SysUserMapper {
    SysUser queryByUserName(String userName);
}
