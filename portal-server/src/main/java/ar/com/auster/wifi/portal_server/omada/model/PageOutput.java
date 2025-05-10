package ar.com.auster.wifi.portal_server.omada.model;

import java.util.List;

public class PageOutput<T> {

    protected int totalRows;
    protected int currentPage;
    protected int currentSize;
    protected List<T> data;

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public List<T>  getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
