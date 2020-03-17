package com.layne.my.taobao.domain;

import com.layne.my.taobao.commons.persistnce.BaseTreeEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 分类管理
 *
 *  @JsonProperty(value = "isParent")设置属性别名
 */
@Data
public class TbContentCategory extends BaseTreeEntity {
    @Length(min = 1,max = 20,message = "分类名称长度必须介于 1 和 20 之间")
    private String name;
    private Integer status;
    @NotNull(message = "排序不能为空")
    private Integer sortOrder;
    private Boolean isParent;
    private TbContentCategory parent;
}
