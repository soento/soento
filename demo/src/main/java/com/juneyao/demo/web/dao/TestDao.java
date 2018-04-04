package com.juneyao.demo.web.dao;

import com.juneyao.demo.web.pojo.po.TestPo;
import com.juneyao.demo.web.pojo.bo.TestBo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestDao {

    Long insert(TestBo entity);

    Long delete(String id);

    Long update(TestBo entity);

    List<TestBo> select(TestBo query);

}
