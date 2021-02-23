package com.qd.server.query;

import com.qd.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 数据字典项查询条件
 * </p>
 *
 * @author 周琦
 * @since 2021-02-22
 */
@Data
public class DiscItemQuery extends BaseQuery {

    /**
     * 字典项名称
     */
    private String name;

    /**
     * 排序
     */
    private Long orders;

    /**
     * 备注
     */
    private String remark;

}
