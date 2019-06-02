package com.story.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * {
 code: 0,
 msg: "",
 count: 1000,
 data: []
 }
 * @param <T>
 */
@SuppressWarnings("serial")
public class LayGridPage<T> implements Serializable {

    private int code = 0;
    private long count = 0;
    private String msg;
    private List<T> data = new ArrayList<>();

    public LayGridPage(){

    }

    public LayGridPage(GridPage gridPage){

        this.setData(gridPage.getRows());
        this.setMsg("");
        this.setCount(gridPage.getTotal());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
