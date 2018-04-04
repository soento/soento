package com.soento.core.support;

import com.soento.core.dao.SequenceDao;
import com.soento.core.enums.DateFormat;
import com.soento.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author yantao.zeng
 */
public abstract class AbstractService {
    @Autowired
    protected MessageSourceAccessor msa;
    @Autowired
    private SequenceDao sequenceDao;

    public String nextseq(String sequence) {
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

    public String nextseq(String sequence, String type) {
        if (StringUtil.isBlank(type) || type.length() != 4) {
            throw new RuntimeException("序列类型标识非四位字符");
        }
        String time = DateFormat.YYYYMMDDHHMISSMS.instance().format(new Date());
        return type.toUpperCase() + time + sequenceDao.nextseq(sequence);
    }
}
