package com.workman.check;

import io.micrometer.core.instrument.util.StringUtils;

public class ExceptionUtils extends Exception {
    private int errCode;

    private String errMsg;

    private Throwable causeThrowable;

    public ExceptionUtils() {
    }

    public ExceptionUtils(int errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public ExceptionUtils(int errCode, String errMsg, Throwable throwable) {
        super(throwable);
        this.errCode = errCode;
        this.errMsg = errMsg;
        setCauseThrowable(throwable);
    }

    public ExceptionUtils(int errCode, Throwable throwable) {
        super(throwable);
        this.errCode = errCode;
        setCauseThrowable(throwable);
    }

    private Throwable getCauseThrowable(Throwable t) {
        if (t.getCause() == null) {
            return t;
        }
        return getCauseThrowable(t.getCause());
    }

    @Override
    public String toString() {
        return "ErrCode:" + getErrCode() + ", ErrMsg:" + getErrMsg();
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        //return errMsg;
        if (!StringUtils.isBlank(this.errMsg)) {
            return errMsg;
        }
        if (this.causeThrowable != null) {
            return causeThrowable.getMessage();
        }
        return "";
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Throwable getCauseThrowable() {
        return causeThrowable;
    }

    public void setCauseThrowable(Throwable causeThrowable) {
        this.causeThrowable = causeThrowable;
    }
}
