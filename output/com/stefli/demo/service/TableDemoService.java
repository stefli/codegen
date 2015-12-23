package com.stefli.demo.service;

import java.util.Map;

import com.stefli.demo.entity.TableDemoEntity;

public interface TableDemoService {

	/**
	 * 按 PK读取实体数据
	 * 
	 * @param aKey
	 * @return TableDemoEntity
	 */
	public TableDemoEntity read(String aKey);

}
