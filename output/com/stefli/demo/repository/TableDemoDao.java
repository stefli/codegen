package com.stefli.demo.repository;

import java.util.List;
import java.util.Map;

import com.stefli.demo.entity.TableDemoEntity;

public interface TableDemoDao {

	/**
	 * 按 PK读取实体数据
	 * 
	 * @param pk
	 * @return TableDemoEntity
	 */
	public TableDemoEntity read(String pk);

}
