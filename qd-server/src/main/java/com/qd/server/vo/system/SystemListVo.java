package com.qd.server.vo.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 基础配置列表Vo
 * </p>
 *
 * @author 周琦
 * @since 2021-02-22
 */
@Data
public class SystemListVo {

    /**
    * 基础配置ID
    */
    private Integer id;

    /**
     * 主键
     */
    private String systemId;

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private String value;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Integer orders;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 更新时间
     */
    private Integer updateTime;

    /**
     * 是否删除
     */
    private Integer isDeleted;

}