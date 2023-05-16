package cn.mediinfo.springdemo.response;

public enum ResponseCodeEnum {
    /**
     * 成功
     */
    SUCCESS(0, "请求成功！"),
    WEIZHIYC(-1, "WEIZHIYC！"),
    KUANGJIAYC(-10, "KUANGJIAYC！"),
    SHUJUKYC(-20, "SHUJUKYC！"),
    SHOUQUANYC(-30, "SHOUQUANYC！"),
    YUANCHENGYC(-40, "YUANCHENGYC！"),
    DAIMAYC(-50, "DAIMAYC！"),
    DUANLUQI(-51, "DUANLUQI！"),
    CAOZUOQX(-52, "CAOZUOQX！"),
    CHAOSHIYC(-53, "CHAOSHIYC！"),
    CANSHUYC(-60, "CANSHUYC！"),
    WEIZHAODSJYC(-61, "WEIZHAODSJYC！");

    private Integer code;
    private String message;

    ResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
