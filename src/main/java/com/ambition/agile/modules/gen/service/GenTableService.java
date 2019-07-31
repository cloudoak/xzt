/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.gen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.BaseService;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.gen.dao.GenDataBaseDictDao;
import com.ambition.agile.modules.gen.dao.GenTableColumnDao;
import com.ambition.agile.modules.gen.dao.GenTableDao;
import com.ambition.agile.modules.gen.entity.GenTable;
import com.ambition.agile.modules.gen.entity.GenTableColumn;
import com.ambition.agile.modules.gen.util.GenUtils;

/**
 * 业务表Service
 * @author harry
 * @version 2013-10-15
 */
@Service
@Transactional(readOnly = true)
public class GenTableService extends BaseService {

	@Autowired
	private GenTableDao genTableDao;
	@Autowired
	private GenTableColumnDao genTableColumnDao;
	@Autowired
	private GenDataBaseDictDao genDataBaseDictDao;
	
	public GenTable get(Integer id) {
		GenTable genTable = genTableDao.get(id);
		GenTableColumn genTableColumn = new GenTableColumn();
		genTableColumn.setGenTable(new GenTable(genTable.getId()));
		genTable.setColumnList(genTableColumnDao.findList(genTableColumn));
		return genTable;
	}
	
	public Page<GenTable> find(Page<GenTable> page, GenTable genTable) {
		genTable.setPage(page);
		page.setList(genTableDao.findList(genTable));
		return page;
	}

	public List<GenTable> findAll() {
		return genTableDao.findAllList(new GenTable());
	}
	
	/**
	 * 获取物理数据表列表
	 * @param genTable
	 * @return
	 */
	public List<GenTable> findTableListFormDb(GenTable genTable){
		return genDataBaseDictDao.findTableList(genTable);
	}
	
	/**
	 * 验证表名是否可用，如果已存在，则返回false
	 * @param genTable
	 * @return
	 */
	public boolean checkTableName(String tableName){
		if (StringUtils.isBlank(tableName)){
			return true;
		}
		GenTable genTable = new GenTable();
		genTable.setName(tableName);
		List<GenTable> list = genTableDao.findList(genTable);
		return list.size() == 0;
	}
	
	/**
	 * 获取物理数据表列表
	 * @param genTable
	 * @return
	 */
	public GenTable getTableFormDb(GenTable genTable){
		// 如果有表名，则获取物理表
		if (StringUtils.isNotBlank(genTable.getName())){
			
			List<GenTable> list = genDataBaseDictDao.findTableList(genTable);
			if (list.size() > 0){
				
				// 如果是新增，初始化表属性
				if (genTable!=null && genTable.getId()==null || genTable.getId()==0){
					genTable = list.get(0);
					// 设置字段说明
					if (StringUtils.isBlank(genTable.getComments())){
						genTable.setComments(genTable.getName());
					}
					genTable.setClassName(StringUtils.toCapitalizeCamelCase(genTable.getName()));
				}
				
				// 添加新列
				List<GenTableColumn> columnList = genDataBaseDictDao.findTableColumnList(genTable);
				for (GenTableColumn column : columnList){
					boolean b = false;
					for (GenTableColumn e : genTable.getColumnList()){
						if (e.getName().equals(column.getName())){
							b = true;
						}
					}
					if (!b){
						genTable.getColumnList().add(column);
					}
				}
				
				// 删除已删除的列
				for (GenTableColumn e : genTable.getColumnList()){
					boolean b = false;
					for (GenTableColumn column : columnList){
						if (column.getName().equals(e.getName())){
							b = true;
						}
					}
					if (!b){
						e.setDelFlag(GenTableColumn.DEL_FLAG_DELETE);
					}
				}
				
				// 获取主键
				genTable.setPkList(genDataBaseDictDao.findTablePK(genTable));
				
				// 初始化列属性字段
				GenUtils.initColumnField(genTable);
				
			}
		}
		return genTable;
	}
	
	@Transactional(readOnly = false)
	public void save(GenTable genTable) {
		if (genTable!=null && genTable.getId()==null || genTable.getId()==0){
			genTable.preInsert();
			genTableDao.insert(genTable);
		}else{
			genTable.preUpdate();
			genTableDao.update(genTable);
		}
		// 保存列
		for (GenTableColumn column : genTable.getColumnList()){
			column.setGenTable(genTable);
			if (column!=null && column.getId()==null || column.getId()==0){
				column.preInsert();
				genTableColumnDao.insert(column);
			}else{
				column.preUpdate();
				genTableColumnDao.update(column);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(GenTable genTable) {
		genTableDao.delete(genTable);
		genTableColumnDao.deleteByGenTableId(genTable.getId());
	}
	
}
