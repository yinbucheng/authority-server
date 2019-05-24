package cn.bucheng.service;

import cn.bucheng.model.bo.FileInitBO;
import cn.bucheng.model.po.FileRecordPO;
import cn.bucheng.model.vo.FileInitVO;
import com.baomidou.mybatisplus.service.IService;

public interface FileRecordService extends IService<FileRecordPO> {

    FileInitBO findPartitionFile(FileInitVO vo);

    void uploadPartitionFile() throws Exception;
}
