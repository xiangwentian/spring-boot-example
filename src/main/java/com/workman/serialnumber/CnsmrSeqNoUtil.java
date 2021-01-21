package com.workman.serialnumber;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author liuzh
 */
public class CnsmrSeqNoUtil {

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                System.out.println("流水号 = " + getCnsmrSeqNo());
//            }).start();
//        }


//        String serialNum = "V93921SH21011133362198";
//        System.out.println("sub str = " + serialNum.substring(6, 8));
//        long start = System.currentTimeMillis();
//        // StringUtils.contains(serialNum, "SH") 效率比substring慢大概10倍
//        if ("SH".equals(serialNum.substring(6, 8))) {
//            System.out.println(true);
//        }
//        System.out.println("substring cost = " + (System.currentTimeMillis() - start));


//        start = System.currentTimeMillis();
//        if (StringUtils.contains(serialNum, "SH")) {
//            System.out.println(true);
//        }
//        System.out.println("contains cost = " + (System.currentTimeMillis() - start));

        System.out.println("流水号 = " + getCnsmrSeqNo());
    }

    /**
     * 组装流水号
     *
     * @return
     */
    public static String getCnsmrSeqNo() {
        //文件传输用户短号，银行分配
        String uid = "V93921";
        String type = "SH";
//        String type = "00";
        //设置日期格式
        SimpleDateFormat df2 = new SimpleDateFormat("yyMMdd");
        //获取当前日期
        String trandate = df2.format(new Date());
        Random random = new Random();
        int ird = random.nextInt(9999999);
        //生成10位随机数
        String srd = String.format("%8d", ird);
        //当有空格时，把空格替换为随机数0-9
        String rsrd = srd.replaceAll(" ", getOneRandomNum());
//        String rsrd = srd.replaceAll(" ", "A");
//        return uid + trandate + srd;
//        return uid + trandate + rsrd;
        return uid + type + trandate + rsrd;
//        return trandate.trim() + srd.trim();
    }

    /**
     * 生成一位随机数
     *
     * @return
     */
    private static String getOneRandomNum() {
        Random random = new Random();
        int ends = random.nextInt(9);
        return String.format("%d", ends);
    }

    /**
     * 生成两位随机数
     *
     * @return
     */
    private String get2Nums() {
        Random random = new Random();
        int ends = random.nextInt(99);
        return String.format("%02d", ends);
    }

}
