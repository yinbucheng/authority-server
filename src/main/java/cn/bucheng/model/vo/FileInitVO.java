package cn.bucheng.model.vo;

import java.io.Serializable;

/**
 * @ClassName FileInitVO
 * @Author buchengyin
 * @Date 2019/5/24 17:27
 **/
public class FileInitVO implements Serializable {
    private String uuid;
    private String fileName;
    private Long fileLength;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileLength() {
        return fileLength;
    }

    public void setFileLength(Long fileLength) {
        this.fileLength = fileLength;
    }
}
