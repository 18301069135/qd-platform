package com.qd.server.query;

import com.qd.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 数据字典集查询条件
 * </p>
 *
 * @author 周琦
 * @since 2021-02-22
 */
@Data
public class DiscQuery extends BaseQuery {

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Long orders;

}
