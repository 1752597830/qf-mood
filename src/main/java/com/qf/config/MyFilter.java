//package com.qf.config;
//
//import com.alibaba.fastjson.JSONObject;
//import com.qf.utils.BodyReaderHttpServletRequestWrapper;
//import com.qf.utils.HttpMethodUtil;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Enumeration;
//import java.util.Map;
//import java.util.function.BiConsumer;
//
//
///**
// * @author: sin
// * @Date: 2023/6/4 - 06 - 04 - 15:03
// * @Description: com.qf.config
// * @version: 1.0
// */
//@Configuration
//public class MyFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    /**
//     * 拦截器对请求进行签名校验，防数据篡改
//     *
//     * @param servletRequest
//     * @param servletResponse
//     * @param filterChain
//     * @throws IOException
//     * @throws ServletException
//     */
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
//        ServletRequest requestWrapper = null;
//        Map<String, String> Params = null;
//        Map<String, String> bodyParams = null;
//
//        if (servletRequest instanceof HttpServletRequest) {
//            try {
//                requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) servletRequest);
//                bodyParams = HttpMethodUtil.getBodyParams((HttpServletRequest) requestWrapper);
//                Params = HttpMethodUtil.getURLParams((HttpServletRequest) requestWrapper);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//        //将参数名封装到map中并进行排序
//
//        //进行md5加密获取sign
//
//        //将加密后的sign与前端传入的sign进行比较
//        // 防止流读取一次后就没有了, 所以需要将流继续写出去
//
//
//        //获取请求中的流如何，将取出来的字符串，再次转换成流，然后把它放入到新request对象中。
//        // 在chain.doFiler方法中传递新的request对象
//        if (requestWrapper == null) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            filterChain.doFilter(requestWrapper, servletResponse);
//        }
//        System.out.println("controller");
//    }
//
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//
//}
