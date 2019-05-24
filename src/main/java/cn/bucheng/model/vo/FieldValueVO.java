package cn.bucheng.model.vo;

import java.io.Serializable;

/**
 * @ClassName FieldValueVO
 * @Author buchengyin
 * @Date 2019/5/22 19:32
 **/
public class FieldValueVO implements Serializable {
    private Long fieldId;
    private String value;

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
