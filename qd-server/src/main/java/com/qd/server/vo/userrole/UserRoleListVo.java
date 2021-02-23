package com.qd.server.vo.userrole;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户角色关联列表Vo
 * </p>
 *
 * @author 周琦
 * @since 2021-02-20
 */
@Data
public class UserRoleListVo {

    /**
    * 用户角色关联ID
    */
    private Integer id;

    /**
     * 
     */
    private String userId;

    /**
     * 
     */
    private String roleId;

}