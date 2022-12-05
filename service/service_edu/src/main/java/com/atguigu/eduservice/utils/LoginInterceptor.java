package com.atguigu.eduservice.utils;

import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.formula.functions.Now;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import sun.util.calendar.BaseCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(StringUtils.isEmpty(request.getHeader("token"))){
            System.out.println("调用拦截器！！！"+ Calendar.getInstance());
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            //UTF-8编码
            httpResponse.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            Map<String,String> map = new HashMap<>();
            map.put("code","28004");
            String json = JSON.toJSONString(map);
            httpResponse.getWriter().print(json);
            return false;
        }else{
            return true;
        }

    }
}
