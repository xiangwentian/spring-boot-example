package com.workman.serialnumber;

public enum MemberTypeEnum {
    COMMERCIAL_TENANT("SH", "商户"),
    MEMBER("00", "会员");

    private String type;
    private String msg;

    private MemberTypeEnum(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static String getMsg(String type) {
        for (MemberTypeEnum mType : MemberTypeEnum.values()) {
            if (mType.getType().equals(type)) {
                return mType.getMsg();
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
