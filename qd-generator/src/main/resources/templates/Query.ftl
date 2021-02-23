package ${packageName}.query;

import com.qd.common.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * ${tableAnnotation}查询条件
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
public class ${entityName}Query extends BaseQuery {

<#if model_column?exists>
    <#list model_column as model>
    <#if model.columnName = 'name'>
    /**
     * ${model.columnComment!}
     */
    private String ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnName = 'code'>
    /**
     * ${model.columnComment!}
     */
    private String ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnName = 'value'>
    /**
     * ${model.columnComment!}
     */
    private String ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnName = 'orders'>
    /**
     * ${model.columnComment!}
     */
    private Long ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnName = 'remark'>
    /**
     * ${model.columnComment!}
     */
    private String ${model.changeColumnName?uncap_first};

    </#if>
    </#list>
</#if>
}
