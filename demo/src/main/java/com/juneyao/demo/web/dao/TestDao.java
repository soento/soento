package com.juneyao.demo.web.dao;

import com.juneyao.demo.web.pojo.entity.TestEntity;
import com.juneyao.demo.web.pojo.query.TestQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestDao {

    Long insert(TestEntity entity);

    Long delete(String id);

    Long update(TestEntity entity);

    List<TestEntity> select(TestQuery query);

}
