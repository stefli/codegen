package ${pkg!}.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import ${pkg!}.entity.${entityName!}Entity;
import ${pkg!}.repository.${entityName!}Dao;

@Repository("${entityName?uncap_first}Dao")
public class ${entityName!}DaoImpl extends MyBatisDao<${entityName!}Entity> implements ${entityName!}Dao {

	private static final Logger logger = Logger.getLogger(${entityName!}DaoImpl.class.getName());

	public ${entityName!}DaoImpl() {
		super();
	}

	/**
	 * 按 PK读取实体数据
	 * 
	 * @param pk
	 * @return ${entityName!}Entity
	 */
	public ${entityName!}Entity read(String pk) {
		return selectOne("${pkg!}.${entityName!}EntityMapper.selectByPrimaryKey", pk);
	}

}
