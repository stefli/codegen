package ${pkg!}.repository;

import java.util.List;
import java.util.Map;

import ${pkg!}.entity.${entityName!}Entity;

public interface ${entityName!}Dao {

	/**
	 * 按 PK读取实体数据
	 * 
	 * @param pk
	 * @return ${entityName!}Entity
	 */
	public ${entityName!}Entity read(String pk);

}
