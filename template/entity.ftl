package ${pkg!}.entity;

<#list columns as column>
<#if column.columnType?default("1")=="93">
import java.sql.Timestamp;
<#break>
</#if>
</#list>

public class ${entityName!}Entity {

	<#list columns as column>
	private <#if (column.columnType?default("1")=="1"||column.columnType?default("1")=="12")>String<#elseif (column.columnType?default("1")=="93")>Timestamp<#else>Long</#if> ${column.columnName?uncap_first};
	</#list>

	public ${entityName!}Entity() {

	}
	<#list columns as column>
	public String get${column.columnName?cap_first}() {
		return this.${column.columnName?uncap_first};
	}

	public void set${column.columnName?cap_first}(String ${column.columnName?uncap_first}) {
		this.${column.columnName?uncap_first} = ${column.columnName?uncap_first};
	}
	</#list>
	
}
