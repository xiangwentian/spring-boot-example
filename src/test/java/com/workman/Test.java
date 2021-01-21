package com.workman;

public class Test {
    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setMsg("hello world <script>");

        new MsgFilter().doFilter(msg);
        System.out.println("msg = " + msg);
    }
}

interface Filter {
    void doFilter(Msg msg);
}

class MsgFilter implements Filter {
    @Override
    public void doFilter(Msg msg) {
        String r = msg.getMsg();
        r = r.replace("<", "[");
        r = r.replace(">", "]");
        msg.setMsg(r);
    }
}

class Msg {

    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
