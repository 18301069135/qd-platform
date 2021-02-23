package com.qd.server.vo.disc;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 数据字典集列表Vo
 * </p>
 *
 * @author 周琦
 * @since 2021-02-22
 */
@Data
public class DiscListVo {

    /**
    * 数据字典集ID
    */
    private Integer id;

    /**
     * 字典集名称
     */
    private String discName;

    /**
     * 字典集名称
     */
    private String discCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 字典集代码
     */
    private String discId;

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