package dev.kienntt.demo.BE_Vinpearl.model;

public class ResponseMessage {
    private int code;

    private String message;

    private Object data;

    private String detailError;

    private Long totalItems;

    private Integer pageIndex;

    private Integer size;

    public ResponseMessage() {

    }

    public ResponseMessage(int code, String message, Object data, String detailError) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.detailError = detailError;
    }

    public ResponseMessage(int code, String message, Object data, Long totalItems, Integer pageIndex, Integer size) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.totalItems = totalItems;
        this.pageIndex = pageIndex;
        this.size = size;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getDetailError() {
        return detailError;
    }

    public void setDetailError(String detailError) {
        this.detailError = detailError;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
