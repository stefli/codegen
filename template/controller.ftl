package ${pkg!}.web;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import ${pkg!}.entity.${entityName!}Entity;
import ${pkg!}.service.${entityName!}Service;

/**
 * 
 * @author stevenl
 * 
 */
@Controller("${entityName?uncap_first}Controller")
public class ${entityName!}Controller {

	private static final Logger logger = Logger.getLogger(${entityName!}Controller.class.getName());

	@Resource
	private ${entityName!}Service ${entityName?uncap_first}Service;

	@DataProvider
	public ${entityName!}Entity read(String id) throws Exception {
		${entityName!}Entity entity = null;
		entity = ${entityName?uncap_first}Service.read(id);
		return entity;
	}

}
