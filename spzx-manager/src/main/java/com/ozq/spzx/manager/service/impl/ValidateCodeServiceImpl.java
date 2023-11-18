package com.ozq.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.ozq.spzx.manager.service.ValidateCodeService;
import com.ozq.spzx.model.enums.LoginEnum;
import com.ozq.spzx.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author ozq
 * @Date 2023/11/14 21:00
 * @Description
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public ValidateCodeVo genValidateCode() {
        // 生成图片，以及图片验证码
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 2);
        String codeValue = circleCaptcha.getCode();
        String imageBase64 = circleCaptcha.getImageBase64();
        // 将图片验证码放入redis,设置过期时间
        String captchaKey = LoginEnum.USER_VALIDATE.getValue() + UUID.randomUUID().toString().replace("_", "");
        stringRedisTemplate.opsForValue().set(captchaKey,codeValue,5, TimeUnit.MINUTES);
        // 返回出去
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(captchaKey);
        validateCodeVo.setCodeValue("data:image/png;base64,"+codeValue);
        return validateCodeVo;
    }
}
