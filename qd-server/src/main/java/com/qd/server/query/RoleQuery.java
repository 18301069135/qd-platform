package com.qd.server.query;

import com.qd.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 角色管理查询条件
 * </p>
 *
 * @author 周琦
 * @since 2021-02-20
 */
@Data
public class RoleQuery extends BaseQuery {

    /**
     * 机构名称
     */
    private String name;

}
