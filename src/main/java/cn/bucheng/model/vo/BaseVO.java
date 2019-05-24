package cn.bucheng.model.vo;

import java.io.Serializable;

/**
 * @ClassName BaseVO
 * @Author buchengyin
 * @Date 2019/5/20 16:28
 **/
public class BaseVO implements Serializable {
    private Integer pageNum;
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
