package ${pkg!}.service;

import java.util.Map;

import ${pkg!}.entity.${entityName!}Entity;

public interface ${entityName!}Service {

	/**
	 * 按 PK读取实体数据
	 * 
	 * @param aKey
	 * @return ${entityName!}Entity
	 */
	public ${entityName!}Entity read(String aKey);

}
