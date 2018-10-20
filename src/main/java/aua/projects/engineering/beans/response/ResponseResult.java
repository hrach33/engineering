package aua.projects.engineering.beans.response;

import java.io.Serializable;

public class ResponseResult implements Serializable {
    private ResponseStatus responseStatus;
    private String message;
    private Object data;

    private ResponseResult(ResponseStatus responseStatus, Object data, String message) {
        this.responseStatus = responseStatus;
        this.data = data;
        this.message = message;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public static ResponseResult ok(){
        return new ResponseResult(ResponseStatus.SUCCESS, null, null);
    }

    public static ResponseResult ok(Object data){
        return new ResponseResult(ResponseStatus.SUCCESS, data, null);
    }

    public static ResponseResult ok(Object data, String message){
        return new ResponseResult(ResponseStatus.SUCCESS, data, message);
    }

    public static ResponseResult error(){
        return new ResponseResult(ResponseStatus.GENERAL_ERROR, null, null);
    }

    public static ResponseResult error(ResponseStatus responseStatus){
        return new ResponseResult(responseStatus, null, null);
    }

    public static ResponseResult error(String errorMessage){
        return new ResponseResult(ResponseStatus.GENERAL_ERROR, null, errorMessage);
    }

    public static ResponseResult error(ResponseStatus responseStatus, String errorMessage){
        return new ResponseResult(responseStatus, null, errorMessage);
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
}