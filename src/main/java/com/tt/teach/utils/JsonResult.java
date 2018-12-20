package com.tt.teach.utils;
/**
 *@作者:wangpeng
 *@时间：2018/12/20 16:13
 *@描述：json的工具类
 */

public class JsonResult {
    //响应状态码
    protected Integer status;
    //响应信息
    protected Object myMsg;
    //响应数据
    protected Object myData;

    public JsonResult(Integer status, Object myMsg, Object myData) {
        this.status = status;
        this.myMsg = myMsg;
        this.myData = myData;
    }

    public JsonResult() {

    }

    public Integer getStatus() {

        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getMyMsg() {
        return myMsg;
    }

    public void setMyMsg(Object myMsg) {
        this.myMsg = myMsg;
    }

    public Object getMyData() {
        return myData;
    }

    public void setMyData(Object myData) {
        this.myData = myData;
    }

    public static  JsonResult ok(String myMsg, Object myData){
        return new JsonResult(200,myMsg,myData);
    }
    public static  JsonResult on(String myMsg,Object myData){
        return new JsonResult(502,myMsg,myData);
    }
}
