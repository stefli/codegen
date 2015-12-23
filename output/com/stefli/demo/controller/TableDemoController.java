package com.stefli.demo.web;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.stefli.demo.entity.TableDemoEntity;
import com.stefli.demo.service.TableDemoService;

/**
 * 
 * @author stevenl
 * 
 */
@Controller("tableDemoController")
public class TableDemoController {

	private static final Logger logger = Logger.getLogger(TableDemoController.class.getName());

	@Resource
	private TableDemoService tableDemoService;

	@DataProvider
	public TableDemoEntity read(String id) throws Exception {
		TableDemoEntity entity = null;
		entity = tableDemoService.read(id);
		return entity;
	}

}
