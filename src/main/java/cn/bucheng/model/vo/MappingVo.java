package cn.bucheng.model.vo;

import java.io.Serializable;

/**
 * @ClassName MappingVo
 * @Author buchengyin
 * @Date 2019/5/20 17:00
 **/
public class MappingVo implements Serializable {
    private String name;
    private Long projectId;
    private String url;
    private String remark;
    private String webId;

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
}
