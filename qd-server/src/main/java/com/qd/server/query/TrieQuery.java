package com.qd.server.query;

import com.qd.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 树形字典查询条件
 * </p>
 *
 * @author 周琦
 * @since 2021-02-22
 */
@Data
public class TrieQuery extends BaseQuery {

    /**
     * 指标名称
     */
    private String name;

    /**
     * 指标代码
     */
    private String code;

    /**
     * 排序
     */
    private Long orders;

    /**
     * 备注
     */
    private String remark;

}
