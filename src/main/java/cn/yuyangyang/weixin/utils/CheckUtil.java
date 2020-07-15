package cn.yuyangyang.weixin.utils;

import java.util.Arrays;

public class CheckUtil {

    // token
    private static final String token = "lxtyyy";

    public static boolean checkSignature(String signature, String timestamp, String nonce){
        System.out.println("进入CheckUtil");
        String[] arr = new String[]{token, timestamp, nonce};
        // 排序
        Arrays.sort(arr);
        // 生成字符串
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        // SHA1加密
        String encode = SHA1.encode(content.toString());//加密后返回的数数值
        System.out.println("encode："+encode);
        System.out.println("signature："+signature);
        System.out.println("结果："+ encode.equals(signature));
        return encode.equals(signature);
    }

}
