package ${packageName}.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qd.common.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * <p>
 * ${tableAnnotation}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("${tableName}")
@SuppressWarnings("serial")
public class ${entityName} extends BaseEntity {

<#if model_column?exists>
    <#list model_column as model>
     <#if model.columnName != 'remark' && model.columnName != 'create_user' && model.columnName != 'create_time' && model.columnName != 'update_time' && model.columnName != 'is_deleted' >
    /**
     * ${model.columnComment!}
     */
    <#if (model.isPk = 1)>
    @TableId(value = "model.columnName", type = IdType.UUID)
    </#if>
    <#if (model.columnType = 'VARCHAR' || model.columnType = 'CHAR' || model.columnType = 'TEXT' || model.columnType = 'MEDIUMTEXT' || model.columnType = 'VARCHAR2')>
    private String ${model.changeColumnName?uncap_first};

    </#if>
    <#if (model.columnType = 'DATETIME' || model.columnType = 'DATE' || model.columnType = 'TIME' || model.columnType = 'YEAR' || model.columnType = 'TIMESTAMP') >
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ${model.changeColumnName?uncap_first};

    </#if>
    <#if (model.columnType = 'TINYINT UNSIGNED' || model.columnType = 'TINYINT')>
    private Integer ${model.changeColumnName?uncap_first};

    </#if>
    <#if (model.columnType = 'SMALLINT UNSIGNED' || model.columnType = 'SMALLINT' || model.columnType = 'MEDIUMINT UNSIGNED' || model.columnType = 'MEDIUMINT')>
    private Integer ${model.changeColumnName?uncap_first};

    </#if>
    <#if (model.columnType = 'INT UNSIGNED' || model.columnType = 'INT' || (model.columnType = 'NUMBER' && model.columnLength=1))>
    private Integer ${model.changeColumnName?uncap_first};

    </#if>
    <#if (model.columnType = 'NUMBER' && model.columnLength>1)>
    private Long ${model.changeColumnName?uncap_first};

    </#if>
    <#if (model.columnType = 'BIGINT UNSIGNED' || model.columnType = 'BIGINT')>
    private Integer ${model.changeColumnName?uncap_first};

    </#if>
    <#if (model.columnType = 'DECIMAL UNSIGNED' || model.columnType = 'DECIMAL')>
    private BigDecimal ${model.changeColumnName?uncap_first};

    </#if>
    <#if (model.columnType = 'FLOAT UNSIGNED' || model.columnType = 'FLOAT')>
    private Float ${model.changeColumnName?uncap_first};

    </#if>
    <#if (model.columnType = 'DOUBLE UNSIGNED' || model.columnType = 'DOUBLE')>
    private Double ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnType = 'BLOB'>
    private byte[] ${model.changeColumnName?uncap_first};
	</#if>
    </#if>
    </#list>
</#if>
}