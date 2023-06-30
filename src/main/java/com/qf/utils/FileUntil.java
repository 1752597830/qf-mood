package com.qf.utils;

import toolgood.words.StringSearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: sin
 * @Date: 2023/6/15 - 06 - 15 - 21:44
 * @Description: com.qf.utils
 * @version: 1.0
 */
public class FileUntil {


    /**
     * 根据文件路径获取到list集合
     * @param filePath
     * @return
     */
    public static List<String> readTxt(String filePath) {
        List<String> list = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 敏感词过滤用*替代
     * @param txt 内容
     * @param replace 敏感词替代字符 '*'
     * @return
     */
    public static String filterSensitivityWord(String txt,char replace){
        String afterValue = txt.replaceAll("\\s+", "");
        String path = Thread.currentThread().getContextClassLoader().getResource("static/dirty.txt").getPath();
        List<String> list = readTxt(path);
        StringSearch words = new StringSearch();
        words.SetKeywords(list);
        System.out.println(afterValue);
        boolean b = words.ContainsAny(afterValue);
        if(b) {
            return words.Replace(txt,replace);
        } else {
            return txt;
        }
    }
}