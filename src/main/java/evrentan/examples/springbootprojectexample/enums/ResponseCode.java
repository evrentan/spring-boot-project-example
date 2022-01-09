package evrentan.examples.springbootprojectexample.enums;

/**
 * Response code and messages.
 *
 * @author zhao wen
 * @since 1.0.0
 **/

public enum ResponseCode {

    SUCCESS(200, "Success", "Success"),

    BAD_REQUEST(400, "Bad Request", "Bad Request"),

    UN_AUTHORIZATION(401, "Authorization Failed", "Authorization Failed"),

    NOT_FOUND(404, "Resource Not Found", "Resource Not Found"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error", "Internal Server Error");

    private Integer code;

    private String msg;

    private String desc;

    ResponseCode(Integer code, String msg, String desc) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }

    public Integer getCode(){ return this.code; }

    public String getMsg(){ return this.msg; }

    public String getDesc(){ return this.desc; }
}
