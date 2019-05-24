package cn.bucheng.model.po;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName TableFieldEntity
 * @Author buchengyin
 * @Date 2019/5/22 16:33
 **/
@TableName("t_table_field")
@Alias("TableField")
public class TableFieldEntity implements Serializable {
    @TableId(type= IdType.AUTO)
    private Long id;
    private String name;
    private Long tableId;
    private Integer type;
    private Boolean isNeed;
    private Integer columnNum;
    private Integer rowNum;
    private Date createTime;
    private String checkRule;
    private String remark;

    final public static int TEXT_INPUT =1;
    final public static int DATE =2;
    final public static int FILE =3;
    final public static int NUMBER =4;
    final public static int SELECT = 5;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getNeed() {
        return isNeed;
    }

    public void setNeed(Boolean need) {
        isNeed = need;
    }

    public Integer getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(Integer columnNum) {
        this.columnNum = columnNum;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCheckRule() {
        return checkRule;
    }

    public void setCheckRule(String checkRule) {
        this.checkRule = checkRule;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
