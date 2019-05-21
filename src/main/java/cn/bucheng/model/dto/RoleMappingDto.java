package cn.bucheng.model.dto;

import java.io.Serializable;

/**
 * @ClassName RoleMappingDto
 * @Author buchengyin
 * @Date 2019/5/21 9:16
 **/
public class RoleMappingDto implements Serializable {
    //项目名称
    private String projectName;
    //模块名称
    private String moduleName;
    //映射名称
    private String mappingName;
    //映射url地址
    private String mappingUrl;
    private Long mappingId;

    public Long getMappingId() {
        return mappingId;
    }

    public void setMappingId(Long mappingId) {
        this.mappingId = mappingId;
    }

    public String getMappingUrl() {
        return mappingUrl;
    }

    public void setMappingUrl(String mappingUrl) {
        this.mappingUrl = mappingUrl;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getMappingName() {
        return mappingName;
    }

    public void setMappingName(String mappingName) {
        this.mappingName = mappingName;
    }
}
