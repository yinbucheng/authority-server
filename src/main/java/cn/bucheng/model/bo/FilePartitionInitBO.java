package cn.bucheng.model.bo;

import java.io.Serializable;

/**
 * @ClassName FilePartitionInitBO
 * @Author buchengyin
 * @Date 2019/5/24 17:29
 **/
public class FilePartitionInitBO implements Serializable {
    private Long id;
    private Long startPosition;
    private Long endPosition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Long startPosition) {
        this.startPosition = startPosition;
    }

    public Long getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Long endPosition) {
        this.endPosition = endPosition;
    }
}
