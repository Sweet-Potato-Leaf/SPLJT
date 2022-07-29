package com.longpengz.utils;

public class StringUtil {


    public static Integer[]  toIntArray(String ids){
        String[] idStringArray = ids.split(",");
        Integer[] idArray = new Integer[idStringArray.length];
        for (int i = 0; i < idStringArray.length; i++) {
            idArray[i] = Integer.parseInt(idStringArray[i]);
        }
        return idArray;
    }

    public static String[] toStringArray(String ids){
        return ids.split(",");
    }
}
