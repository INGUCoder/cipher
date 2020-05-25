package encry;

/**
 * 凯撒加密 和 解密
 */
public class CaesarCipher {

    public static void main(String[] args) {
        //1:明文:要加密的内容
        String clearText= "woammazheng";
        //加密规则:将字母按字母表的顺序向后移5位
        int key = 5;
        //密文：加密成功后的内容
        String cipher = encrypt(clearText,key);
        System.out.println(cipher);
        String unCipher = unencrypt(cipher,key);
        System.out.println(unCipher);

    }

    /**
     * 解密
     * @param cipher
     * @param key
     * @return
     */
    private static String unencrypt(String cipher, int key) {
        char[] chars = cipher.toCharArray();
        for(int i=0;i<chars.length;i++){
            //取出每一个字母 并向前移动五位
            char c = (char) (chars[i]-key);
            chars[i] =c;

        }
        return String.valueOf(chars);
    }

    /**
     * 加密
     * @param clearText
     * @param key
     * @return
     */
    private static String encrypt(String clearText, int key) {
        char[] chars = clearText.toCharArray();
        for(int i=0;i<chars.length;i++){
            //取出每一个字母 并且向后移动5位
            char c = (char) (chars[i]+key);
            chars[i] = c;
        }
        return String.valueOf(chars);
    }



}
