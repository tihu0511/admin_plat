package org.jigang.plat.admin.exception;

/**
 * 管理后台异常
 *
 * @author wjg
 * @date 2017/1/1.
 */
public class AdminException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public AdminException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public AdminException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public AdminException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public AdminException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
