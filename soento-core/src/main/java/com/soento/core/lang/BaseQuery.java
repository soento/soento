package com.soento.core.lang;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author soento
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseQuery extends BaseEntity {
    /**
     * 查询页数
     */
    protected int index;
    /**
     * 分页步长
     */
    protected int limit;
    /**
     * 开始位置
     */
    protected int start;
    /**
     * 结束位置
     */
    protected int end;

    public BaseQuery() {
        setIndex(0);
        setLimit(10);
    }

    public BaseQuery(int index, int limit) {
        setIndex(index);
        setLimit(limit);
    }

    public void setIndex(int index) {
        this.index = index;
        setStart();
        setEnd();
    }

    public void setLimit(int limit) {
        this.limit = limit;
        setStart();
        setEnd();
    }

    private void setStart() {
        this.start = this.index * this.limit;
    }

    private void setEnd() {
        this.end = this.start + this.limit - 1;
    }
}
