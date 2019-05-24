package cn.bucheng.controller;

import cn.bucheng.model.bo.FileInitBO;
import cn.bucheng.model.dto.ServerResult;
import cn.bucheng.model.vo.FileInitVO;
import cn.bucheng.service.FileRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName FileRecordController
 * @Author buchengyin
 * @Date 2019/5/24 17:25
 **/
@RestController
@RequestMapping("/file")
public class FileRecordController {
    @Autowired
    private FileRecordService fileRecordService;

    @RequestMapping("/init")
    public Object initFile(@RequestBody  FileInitVO vo){
        FileInitBO partitionFile = fileRecordService.findPartitionFile(vo);
        return ServerResult.success(partitionFile);
    }

    @RequestMapping("/uploadPartitionFile")
    public Object uploadPartitionFile(){
        try {
            fileRecordService.uploadPartitionFile();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResult.fail(e.getMessage());
        }
        return ServerResult.success();
    }
}
