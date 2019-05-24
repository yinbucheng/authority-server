package cn.bucheng.model.bo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName FileInitBO
 * @Author buchengyin
 * @Date 2019/5/24 17:36
 **/
public class FileInitBO implements Serializable {
    private Long fileId;
    private Boolean finish;
    private List<FilePartitionInitBO> partitions;

    public FileInitBO(){
        partitions = new LinkedList<>();
    }

    public void addFilePartitionInitBo(FilePartitionInitBO bo){
        partitions.add(bo);
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public List<FilePartitionInitBO> getPartitions() {
        return partitions;
    }

    public void setPartitions(List<FilePartitionInitBO> partitions) {
        this.partitions = partitions;
    }
}
