package com.util;

/**
 * Created by mazhejiayu on 8/19/16.
 */

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import java.net.URLEncoder;
/**
 *
 * @author Administrator
 *
 */
public class AESUtil {

    // 加密
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位: "+sKey);
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static String encryptOpenId(String s) throws Exception{
        String result = "";
        Integer cKey = new Date().getMonth();
        String day = cKey.toString();
        String mk = "0000"+day+"010"+day+"00"+day+"00"+day+day;
        mk = mk.substring(0,16);
        System.out.println(mk);
        result = Encrypt(s.toString(),mk).replace("/","$escape$");
        return result;
    }
    public static String decryptOpenId(String s) throws Exception{
        s = s.replace("$escape$","/");;
        Integer cKey = new Date().getMonth();
        String day= cKey.toString();
        String mk = "0000"+day+"010"+day+"00"+day+"00"+day+day;
        mk = mk.substring(0,16);
        String openId = Decrypt(s,mk);
        return openId;
    }

    public static void main(String[] args) throws Exception {
        String a = encryptOpenId("dev-mazhe");
        System.out.println(a);
        System.out.println(decryptOpenId(a));
        System.out.println(new Date().getMonth());
//        /*
//         * 此处使用AES-128-ECB加密模式，key需要为16位。
//         */
//        Long cKey = new Date().getTime();
//        String mk = cKey.toString().substring(0,7)+"000000000";
//        System.out.println(mk);
//        // 需要加密的字串
//        String cSrc = "/fiojg/ad/f".replace("/","$");
//        System.out.println(cSrc);
//        // 加密
//        String enString = AESUtil.Encrypt(cSrc, mk);
//        System.out.println("加密后的字串是：" + enString);
//
//        // 解密
//        String DeString = AESUtil.Decrypt(enString, mk);
//        System.out.println("解密后的字串是：" + DeString);

    }
}



