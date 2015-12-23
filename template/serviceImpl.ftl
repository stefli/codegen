package ${pkg!}.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${pkg!}.entity.${entityName!}Entity;
import ${pkg!}.repository.${entityName!}Dao;
import ${pkg!}.service.${entityName!}Service;

@Service("${entityName?uncap_first}Service")
public class ${entityName!}ServiceImpl implements ${entityName!}Service {

	private static final Logger logger = Logger.getLogger(${entityName!}ServiceImpl.class.getName());
	
	@Autowired
	private ${entityName!}Dao ${entityName?uncap_first}Dao;

	// 按关键字查询
	public ${entityName!}Entity read(final String aKey) {
		return ${entityName?uncap_first}Dao.read(aKey);
	}

}
