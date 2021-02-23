package com.qd.server.query;

import com.qd.common.common.BaseQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资源管理查询条件
 * </p>
 *
 * @author 周琦
 * @since 2021-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceQuery extends BaseQuery {

    /**
     * 资源名称
     */
    private String name;

}
