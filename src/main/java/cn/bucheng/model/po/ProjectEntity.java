package cn.bucheng.model.po;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ProjectEntity
 * @Author buchengyin
 * @Date 2019/5/20 15:26
 **/
@TableName("t_project")
@Alias("Project")
public class ProjectEntity implements Serializable {
    private Long id;
    //项目名称或者模块名称，如果为项目名称parentId为null
    private String name;
    private Date createTime;
    private Date deleteTime;
    private Long parentId;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}
