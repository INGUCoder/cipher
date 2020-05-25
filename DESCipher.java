package encry;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * DES 加密和解密 对比特位进行进行数学运算
 *
 */
public class DESCipher {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        //明文
        String clearText = "woshimazheng";
        //密钥
        String originKey = "123456782121ws9";
        //获取密文
        String cipherText = desEncrypt(clearText,originKey);
        System.out.println(cipherText);
        //解密
        String clearText2 = desDecrypt(cipherText,originKey);
        System.out.println(clearText2);

    }

    /**
     * 解密
     * @param cipherText
     * @param originKey
     * @return
     */
    private static String desDecrypt(String cipherText, String originKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("DES");
        //初始化
        SecretKeySpec key=getKey(originKey);
        cipher.init(Cipher.DECRYPT_MODE,key);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(cipherText);
        byte[] bytes = cipher.doFinal(decode);
        return new String(bytes);
    }

    /**
     * 获取密文
     * @param clearText
     * @param originKey
     * @return
     * @throws NoSuchPaddingException：填充异常(工作模式和填充模式)
     * @throws NoSuchAlgorithmException
     */
    private static String desEncrypt(String clearText, String originKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //获取内置的des算法工具类
        Cipher cipher = Cipher.getInstance("DES");
        //初始化加密工作对象 mode：加密和解密 key:对原始密钥处理后的密钥
        SecretKeySpec key = getKey(originKey);
        cipher.init(Cipher.ENCRYPT_MODE,key);
        //用加密工具类对明文进行加密
        byte[] bytes = cipher.doFinal(clearText.getBytes());
        //解决乱码问题
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(bytes);
        return new String(encode);

    }
    //对原始密钥进行处理
    private static SecretKeySpec getKey(String originKey) {
        //byte数组每个元素默认初始值位为0 验证位长度设置位8
        byte[] buffer = new byte[8];

        byte[] originBytes = originKey.getBytes();

        //如果originBytes超过8个字节 只要8个字节  如果 不足就用默认初始值来填充
        for (int i=0;i<8 && i<originBytes.length;i++){
            buffer[i] = originBytes[i];
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(buffer, "DES");
        return  secretKeySpec;
    }

}
