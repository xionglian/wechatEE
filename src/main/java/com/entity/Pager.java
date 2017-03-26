package com.entity;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengqin on 2017/3/20.
 */
public class Pager {
    private  int currentPageNumber;
    private  int pageSize;
    private  int totalSize;
    private List dataList;


    public Pager(int currentPageNumbe,int pageSize){
        this.currentPageNumber = ((Integer)currentPageNumbe==null ? 1 : currentPageNumbe);
        this.pageSize =((Integer)pageSize==null ? 5 : pageSize) ;
        this.dataList = new ArrayList<T>();
    }
    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public int getTotalPageNum(){
        return totalSize%pageSize == 0 ? totalSize/pageSize : totalSize/pageSize + 1;
    }
    public String getSql(String sql){
        String finalSql="select t.* from ("+sql+") t where rownum<= currentPageNumber*pageSize and rownum> currentPageNumber*pageSize";
        return finalSql;
    }

}

