package com.ozq.spzx.manager.controller;

import com.ozq.spzx.manager.service.ValidateCodeService;
import com.ozq.spzx.model.dto.system.LoginDto;
import com.ozq.spzx.model.vo.common.Result;
import com.ozq.spzx.model.vo.common.ResultCodeEnum;
import com.ozq.spzx.model.vo.system.LoginVo;
import com.ozq.spzx.manager.service.SysUserService;
import com.ozq.spzx.model.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ozq
 * @Date 2023/11/5 20:45
 * @Description
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ValidateCodeService validateCodeService;



    @PostMapping(value = "/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto){

        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }
    @PostMapping(value = "/genValidateCode")
    public Result<ValidateCodeVo> genValidateCode(){

        ValidateCodeVo validateCodeVo = validateCodeService.genValidateCode();
        return Result.build(validateCodeVo,ResultCodeEnum.SUCCESS);
    }

}
