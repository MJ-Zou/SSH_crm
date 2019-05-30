package utils;

import java.util.UUID;

/**
 * 文件上传的工具类
 */
public class UploadUtils {
    public static String getUUIDName(String filename){
        //先查找
        int index=filename.lastIndexOf(".");
        //截取
        String lastname=filename.substring(index,filename.length());
        //唯一字符串
        String uuid= UUID.randomUUID().toString().replace("-","");
        return uuid+lastname;
    }


    public static void main(String[] args){
        String filename="girl.jpg";
        System.out.println(getUUIDName(filename));
    }
}
