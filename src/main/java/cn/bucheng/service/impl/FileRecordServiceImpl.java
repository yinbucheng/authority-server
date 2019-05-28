package cn.bucheng.service.impl;

import cn.bucheng.config.WebContextHolder;
import cn.bucheng.dao.FileRecordMapper;
import cn.bucheng.dao.PartitionFileMapper;
import cn.bucheng.model.bo.FileInitBO;
import cn.bucheng.model.bo.FilePartitionInitBO;
import cn.bucheng.model.constant.Constant;
import cn.bucheng.model.po.FileRecordPO;
import cn.bucheng.model.po.PartitionFileRecordPO;
import cn.bucheng.model.vo.FileInitVO;
import cn.bucheng.service.FileRecordService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * @ClassName FileRecordServiceImpl
 * @Author buchengyin
 * @Date 2019/5/24 17:25
 **/
@Service
public class FileRecordServiceImpl extends ServiceImpl<FileRecordMapper, FileRecordPO> implements FileRecordService {
    @Autowired
    private PartitionFileMapper partitionFileMapper;

    @Override
    public FileInitBO findPartitionFile(FileInitVO vo) {
        List<FileRecordPO> records = baseMapper.selectList(new Condition().eq("tag", vo.getUuid()));

        //这里判断是否已经上传过包
        if (records == null || records.size() == 0) {
            String filePath = "/opt/file/temp/";
            createDirIfNotExist(filePath);
            String file = filePath + System.currentTimeMillis() + "_" + vo.getFileName();
            makEmptyFile(file, vo.getFileLength());
            Long fileId = saveFileRecord(vo, file);
            FileInitBO bo = createFileInitBo(fileId, false);
            if (vo.getFileLength() <= Constant.PARTITION_FILE_MAX_SIZE) {
                PartitionFileRecordPO partitionFileRecordPO = savePartitionFileRecord(fileId, 0, vo.getFileLength());
                appendFileInitBO(bo, partitionFileRecordPO.getId(), partitionFileRecordPO.getStartPosition(), partitionFileRecordPO.getEndPosition());
                return bo;
            }

            if (vo.getFileLength() / Constant.PARTITION_FILE_MAX_SIZE >= Constant.MAX_UPLOAD_PARTION_FILE_SIZE) {
                long eachLength = vo.getFileLength() / Constant.MAX_UPLOAD_PARTION_FILE_SIZE;
                for (int i = 0; i < Constant.MAX_UPLOAD_PARTION_FILE_SIZE; i++) {
                    long endPosition = (i + 1) * eachLength;
                    if (i == Constant.MAX_UPLOAD_PARTION_FILE_SIZE - 1) {
                        endPosition = vo.getFileLength();
                    }
                    PartitionFileRecordPO partitionFileRecordPO = savePartitionFileRecord(fileId, i * eachLength, endPosition);
                    appendFileInitBO(bo, partitionFileRecordPO.getId(), partitionFileRecordPO.getStartPosition(), partitionFileRecordPO.getEndPosition());
                }
                return bo;
            }

            long partitionNum = vo.getFileLength() / Constant.PARTITION_FILE_MAX_SIZE;
            for (int i = 0; i < partitionNum; i++) {
                long endPosition = (i + 1) * Constant.PARTITION_FILE_MAX_SIZE;
                if (i == Constant.MAX_UPLOAD_PARTION_FILE_SIZE - 1) {
                    endPosition = vo.getFileLength();
                }
                PartitionFileRecordPO partitionFileRecordPO = savePartitionFileRecord(fileId, i * Constant.PARTITION_FILE_MAX_SIZE, endPosition);
                appendFileInitBO(bo, partitionFileRecordPO.getId(), partitionFileRecordPO.getStartPosition(), partitionFileRecordPO.getEndPosition());
            }
            return bo;
        }

        FileRecordPO fileRecordPO = records.get(0);
        FileInitBO bo = createFileInitBo(fileRecordPO.getId(), fileRecordPO.getFinishFlag());
        if (fileRecordPO.getFinishFlag()) {
            return bo;
        }

        List<PartitionFileRecordPO> partitionFiles = partitionFileMapper.selectList(new Condition().eq("file_id", fileRecordPO.getId()));
        for (PartitionFileRecordPO partitionFile : partitionFiles) {
            if (partitionFile.getFinishFlag())
                continue;
            appendFileInitBO(bo, partitionFile.getId(), partitionFile.getStartPosition(), partitionFile.getEndPosition());
        }
        return bo;
    }

    @Override
    public void uploadPartitionFile() throws Exception {
        long fileId = WebContextHolder.getHeaderLongValue("fileId");
        long partitionId = WebContextHolder.getHeaderLongValue("partitionId");
        long startPosition = WebContextHolder.getHeaderLongValue("startPosition");
        long endPosition = WebContextHolder.getHeaderLongValue("endPosition");
        PartitionFileRecordPO partitionFile = partitionFileMapper.selectById(partitionId);
        if (partitionFile == null) {
            throw new RuntimeException("未找到上传文件记录");
        }
        if (partitionFile.getStartPosition() < startPosition || partitionFile.getEndPosition() > endPosition) {
            throw new RuntimeException("上传文件的偏移量非法");
        }
        FileRecordPO fileRecord = baseMapper.selectById(fileId);
        if (fileRecord == null) {
            throw new RuntimeException("未找到对应文件记录");
        }
        baseMapper.updateTime(fileId,new Date());
        partitionFileMapper.updateTime(partitionId,new Date());
        RandomAccessFile randomAccessFile = null;
        BufferedInputStream bis = null;
        try {
            randomAccessFile = new RandomAccessFile(fileRecord.getFilePath(), "rw");
            randomAccessFile.seek(startPosition);
            bis = new BufferedInputStream(WebContextHolder.getRequest().getInputStream());
            byte[] buffer = new byte[1024 * 10];
            int len = -1;
            int count = 0;
            while ((len = bis.read(buffer)) != -1) {
                count += len;
                randomAccessFile.write(buffer, 0, len);
                if (count % Constant.MAX_BUFFER_SIZE == 0) {
                    partitionFileMapper.updateStartPosition(partitionId, startPosition + count);
                }
            }
            partitionFileMapper.updateStartPosition(partitionId, startPosition + count);
            partitionFileMapper.updateFinishState(partitionId);
            baseMapper.updateFileFinishFlag(fileId);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        } finally {
            closeStream(randomAccessFile);
            closeStream(bis);
        }
    }

    private void createDirIfNotExist(String filePath) {
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private void makEmptyFile(String file, long fileLength) {
        RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile(file, "rw");
            rf.setLength(fileLength);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeStream(rf);
        }
    }

    private FileInitBO createFileInitBo(long fileId, boolean finish) {
        FileInitBO fileInitBO = new FileInitBO();
        fileInitBO.setFileId(fileId);
        fileInitBO.setFinish(finish);
        return fileInitBO;
    }

    private FileInitBO appendFileInitBO(FileInitBO bo, long partitionId, Long startPosition, Long endPosition) {
        FilePartitionInitBO partition = new FilePartitionInitBO();
        partition.setStartPosition(startPosition);
        partition.setEndPosition(endPosition);
        partition.setId(partitionId);
        bo.addFilePartitionInitBo(partition);
        return bo;
    }

    private PartitionFileRecordPO savePartitionFileRecord(long fileId, long startPosition, long endPosition) {
        PartitionFileRecordPO po = new PartitionFileRecordPO();
        po.setFileId(fileId);
        po.setStartPosition(startPosition);
        po.setEndPosition(endPosition);
        po.setCreateTime(new Date());
        po.setFinishFlag(false);
        po.setDeleteFlag(false);
        partitionFileMapper.insert(po);
        return po;
    }

    private Long saveFileRecord(FileInitVO vo, String file) {
        FileRecordPO po = new FileRecordPO();
        po.setCreateTime(new Date());
        po.setFileLength(vo.getFileLength());
        po.setTag(vo.getUuid());
        po.setFilePath(file);
        po.setDeleteFlag(false);
        po.setFinishFlag(false);
        po.setFileName(vo.getFileName());
        baseMapper.insert(po);
        return po.getId();
    }

    private void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println(e);
            }
        }
    }
}
