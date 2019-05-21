package cn.bucheng.model.dto;

/**
 * @ClassName ServerResult
 * @Author buchengyin
 * @Date 2019/5/20 16:14
 **/
public class ServerResult {
    private int code;
    private String message;
    private Object data;

    public ServerResult(){

    }

    public ServerResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
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

    public static ServerResult success(){
       return new ServerResult(200,"执行成功",null);
    }

    public static ServerResult success(Object data){
        return new ServerResult(200,"执行成功",data);
    }

    public static ServerResult fail(){
        return new ServerResult(500,"执行失败",null);
    }

    public static ServerResult fail(String message){
        return new ServerResult(500,message,null);
    }

}
