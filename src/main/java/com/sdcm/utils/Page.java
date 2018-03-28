package com.sdcm.utils;

import java.util.List;

/**
 * 分页
 * @author XUSANDUO
 *
 * @param <T>
 */
public class Page<T> {

    private int currentPage=1;
    private int maxPage;
    private int limit = 2;
    private int total;
    private String sort;
    private List<Integer> pageNum;
    private List<T> resultlist;
    private int s;
    public Page() {
    }

    /**
     * @param currentPage 当前页
     * @param maxPage 最大页
     * @param pageNum 页码数组
     * @param resultlist 数据集
     */
    public Page(int currentPage, int maxPage, int total, List<Integer> pageNum, List<T> resultlist) {
        this.currentPage = currentPage;
        this.maxPage = maxPage;
        this.total = total;
        this.pageNum = pageNum;
        this.resultlist = resultlist;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getResultlist() {
        return resultlist;
    }

    public void setResultlist(List<T> resultlist) {
        this.resultlist = resultlist;
    }

    public void setPageNum(List<Integer> pageNum) {
        this.pageNum = pageNum;
    }

    public List<Integer> getPageNum() {
        return pageNum;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @return the sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * @param sort the sort to set
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * @return the s
     */
    public int getS() {
        return s;
    }

    /**
     * @param s the s to set
     */
    public void setS(int s) {
        this.s = s;
    }

}
