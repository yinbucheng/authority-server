package cn.bucheng.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName MappingRoleDTO
 * @Author buchengyin
 * @Date 2019/5/21 13:46
 **/
public class MappingRoleDTO implements Serializable {
    private Long mappingId;
    private String name;
    private Boolean check;
    private String url;
    private String remark;
    private Date createTime;

    public Long getMappingId() {
        return mappingId;
    }

    public void setMappingId(Long mappingId) {
        this.mappingId = mappingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
