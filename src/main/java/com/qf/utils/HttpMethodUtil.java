package com.qf.utils;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import springfox.documentation.spring.web.json.Json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sin
 * @Date: 2023/5/22 - 05 - 22 - 11:04
 * @Description: com.qf.utils
 * @version: 1.0
 */
public class HttpMethodUtil {
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取请求头或者请求体参数
     * @param request
     * @return
     * @throws Exception
     */

    public static Map<String, String> getURLParams(HttpServletRequest request) throws Exception {
        String queryParam = request.getQueryString();
        if(queryParam == null) {
            return null;
        }
        queryParam = URLDecoder.decode(queryParam, "UTF-8");
        Map<String, String> result = new HashMap<>();
        String[] split = queryParam.split("&");
        for(String s : split) {
            int i = s.indexOf("=");
            result.put(s.substring(0,i),s.substring(i+1));
        }
        System.out.println("GET数据：" + result);
        return  result;
    }
    public static Map<String, String> getBodyParams(HttpServletRequest request) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder sb = new StringBuilder();
        //读取流
        String s = "";
        while((s= reader.readLine()) != null) {
            sb.append(s);
        }
        Map map = JSONObject.parseObject(sb.toString(), Map.class);
        System.out.println("Body数据：" + map);
        return map;
    }
    public static Map<String, String> getAllParams(HttpServletRequest request) throws Exception {
        Map<String, String> bodyParams = HttpMethodUtil.getBodyParams(request);
        Map<String, String> Params = HttpMethodUtil.getURLParams(request);
        return null;
    }
}
