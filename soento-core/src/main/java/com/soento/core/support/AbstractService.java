package com.soento.core.support;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soento.core.dao.SequenceDao;
import com.soento.core.enums.DateFormat;
import com.soento.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author yantao.zeng
 */
public abstract class AbstractService {
    @Autowired
    protected MessageSourceAccessor msa;
    @Autowired
    protected SequenceDao sequenceDao;

    protected String nextseq(String sequence) {
        StringBuilder type = new StringBuilder(getClass().getSimpleName());
        if (type.length() < 4) {
            while (type.length() < 4) {
                type = type.append(9);
            }
            return nextseq(sequence, type.toString());
        } else {
            return nextseq(sequence, type.substring(0, 4));
        }
    }

    protected String nextseq(String sequence, String type) {
        if (StringUtil.isBlank(type) || type.length() != 4) {
            throw new RuntimeException("序列类型标识非四位字符");
        }
        String time = DateFormat.YYYYMMDDHHMISSMS.instance().format(new Date());
        return type.toUpperCase() + time + sequenceDao.nextseq(sequence);
    }

    protected <T> Page<T> startPage() {
        return startPage(1, 10);
    }

    protected <T> Page<T> startPage(int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize);
    }

    protected <T> PageInfo<T> pageInfo(List<T> searchList) {
        return new PageInfo(searchList);
    }
}
