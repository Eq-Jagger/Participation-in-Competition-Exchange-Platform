package com.dao;

import com.entity.LaoshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.LaoshiView;
/*

   Dao（Data Access Object）接口是一种用于封装数据访问逻辑的接口层。
它负责与数据库直接交互，包括执行增删改查（CRUD）操作以及执行复杂的查询语句。
Dao 层的主要职责是将数据的存取逻辑与业务逻辑分离，
使得业务层可以专注于业务逻辑而不必关心底层的数据库访问细节。


 */
/**
 * 老师 Dao 接口
 *
 * @author 
 */
public interface LaoshiDao extends BaseMapper<LaoshiEntity> {

   List<LaoshiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
