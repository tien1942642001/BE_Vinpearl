package dev.kienntt.demo.BE_Vinpearl.model;

public class ResponsePage {
    private int code;

    private String message;

    private Object data;

    private String detailError;

    private Long totalItems;

    public ResponsePage() {

    }

    public ResponsePage(int code, String message, Object data, String detailError) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.detailError = detailError;
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
}
