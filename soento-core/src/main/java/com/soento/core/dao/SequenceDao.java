package com.soento.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SequenceDao {

    @Select("select CURRVAL(#{name}) from dual")
    Long currval(String sequence);

    @Select("select NEXTVAL(#{name}) from dual")
    Long nextval(String sequence);

    @Select("select LPAD(CURRVAL(#{name}), 11, '0') from dual")
    String currseq(String sequence);

    @Select("select LPAD(NEXTVAL(#{name}), 11, '0') from dual")
    String nextseq(String sequence);
}
