package com.wiseweb.entity;

import java.util.List;
import java.util.Map;

/**
 * Created by Chory on 2017/2/24 0024.
 * Hbase分页对象
 */
public class HbasePageBean {

    private Integer currentPage;
    private Integer pageSize;
    private Integer totalCount;
    private Integer totalPage;
    private List<Map<String, String>> resultList;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<Map<String, String>> getResultList() {
        return resultList;
    }

    public void setResultList(List<Map<String, String>> resultList) {
        this.resultList = resultList;
    }
}
