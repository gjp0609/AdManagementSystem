package me.rainbow.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

/**
 * @author guojinpeng
 * @date 17.8.22 18:27
 */
public class StringUtil {
    private static final Logger log = LoggerFactory.getLogger(StringUtil.class);

    public enum Type {
        LOWER("纯小写字母", "abcdefghijklmnopqrstuvwxyz"),
        UPPER("纯大写字母", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        NUMBER("纯数字", "0123456789");

        private String name;
        private String value;

        Type(String name, String index) {
            this.name = name;
            this.value = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * 产生指定位数随机字符串
     *
     * @param length 长度
     * @param types  字符串类型
     * @return 随机码
     * @date 17.8.22
     */
    public static String getRandomString(int length, Type... types) {
        log.info("获取随机字符串，长度为{}，类型为{}", length, types);
        // 生成源
        StringBuilder src = new StringBuilder();
        if (types.length > 0)
            for (Type type : types) {
                src.append(type.value);
            }
        else src.append(Type.NUMBER);
        Random random = new Random((long) (Math.random() * 1000));
        String string = "";
        int i;
        for (int j = 0; j < length; j++) {
            i = random.nextInt(src.length());
            string = string.concat(String.valueOf(src.charAt(i)));
        }
        return string;
    }

    /**
     * 获取去除短横线的随机UUID
     *
     * @return 32位uuid
     */
    public static String getRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 得到彩色效果的字符串
     * 支持彩色输出的控制台
     * 可写入log得到彩色日志
     *
     * @param src 原字符串
     * @return 变色后的字符串
     */
    public static String getColorfulString(String src, Color... colors) {
        StringBuilder s = new StringBuilder();
        for (Color color : colors) {
            s.append("\033[");
            s.append(color.getValue());
            s.append("m");
        }
        s.append(src);
        s.append("\033[0m");
        return s.toString();
    }

    public static void main(String[] args) {
        String s = getRandomString(10, Type.NUMBER);
        s = StringUtil.getColorfulString(s, Color.PU, Color.INVERT);
        System.out.println(s);
    }
}
