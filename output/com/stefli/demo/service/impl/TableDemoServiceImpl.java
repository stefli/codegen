package com.stefli.demo.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefli.demo.entity.TableDemoEntity;
import com.stefli.demo.repository.TableDemoDao;
import com.stefli.demo.service.TableDemoService;

@Service("tableDemoService")
public class TableDemoServiceImpl implements TableDemoService {

	private static final Logger logger = Logger.getLogger(TableDemoServiceImpl.class.getName());
	
	@Autowired
	private TableDemoDao tableDemoDao;

	// 按关键字查询
	public TableDemoEntity read(final String aKey) {
		return tableDemoDao.read(aKey);
	}

}
