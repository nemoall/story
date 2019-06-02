package com.story.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class GridPage<T> implements Serializable {

    private long total = 1;
    private List<T> rows = new ArrayList<>();

    @SuppressWarnings({"rawtypes", "unchecked"})

    public GridPage(List<T> list,long total) {
        setTotal(total);
        setRows(list);
    }

    public GridPage() {
        super();
    }


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }


    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
