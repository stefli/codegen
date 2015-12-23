package com.stefli.demo.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.stefli.demo.entity.TableDemoEntity;
import com.stefli.demo.repository.TableDemoDao;

@Repository("tableDemoDao")
public class TableDemoDaoImpl extends MyBatisDao<TableDemoEntity> implements TableDemoDao {

	private static final Logger logger = Logger.getLogger(TableDemoDaoImpl.class.getName());

	public TableDemoDaoImpl() {
		super();
	}

	/**
	 * 按 PK读取实体数据
	 * 
	 * @param pk
	 * @return TableDemoEntity
	 */
	public TableDemoEntity read(String pk) {
		return selectOne("com.stefli.demo.TableDemoEntityMapper.selectByPrimaryKey", pk);
	}

}
