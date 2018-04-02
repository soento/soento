package com.juneyao.demo.web.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.juneyao.demo.web.entity.TestEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper extends BaseMapper<TestEntity> {

    List<TestEntity> getAll();
}
