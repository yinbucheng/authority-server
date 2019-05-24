package cn.bucheng.model.constant;

/**
 * @ClassName Constant
 * @Author buchengyin
 * @Date 2019/5/24 17:39
 **/
public class Constant {
    //一个文件分区最大为500m
    public static final long PARTITION_FILE_MAX_SIZE = 1024*1024*500;
    //最大的线程数量
    public static final int MAX_UPLOAD_PARTION_FILE_SIZE = 4;
    //5m刷新下库
    public static final int MAX_BUFFER_SIZE = 1024*1024*5;
}
