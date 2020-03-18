package com.layne.my.taobao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.layne.my.taobao.commons.persistnce.BaseEntity;
import com.layne.my.taobao.commons.utils.RegexpUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(callSuper = true)
public class TbUser extends BaseEntity {

    @Length(min = 6, max = 20, message = "用户名长度必须介于 6 和 20 之间")
    private String username;
    @JsonIgnore
    @Length(min = 6, max = 20, message = "密码长度必须介于 6 和 20 之间")
    private String password;
    @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
    private String phone;
    @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
    private String email;

}
