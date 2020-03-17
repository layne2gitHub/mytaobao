package com.layne.my.taobao.commons.persistnce;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseTreeEntity<T extends BaseEntity>  extends BaseEntity implements Serializable {

    private T parent;

    private Boolean isParent;
}
