package cn.bucheng.config;

import org.apache.logging.log4j.util.Strings;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName WebContextHolder
 * @Author buchengyin
 * @Date 2019/5/24 19:27
 **/
public class WebContextHolder {

    private static ServletRequestAttributes getRequestAttributes() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes;
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static String getHeaderValue(String key){
        return getRequest().getHeader(key);
    }

    public static long getHeaderLongValue(String key){
        String value = getHeaderValue(key);
        if(value==""){
           return 0;
        }
        return Long.parseLong(value);
    }
}
