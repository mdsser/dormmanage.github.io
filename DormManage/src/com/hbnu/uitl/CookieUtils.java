package com.hbnu.uitl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    public static void addCookie(String cookieName, int time, HttpServletRequest request, HttpServletResponse response,String stuCode,String password){
        Cookie cookie = getCookieByName(request,cookieName);
        if(cookie !=null){
            cookie.setValue(stuCode+"#"+password);
        }else{
            cookie = new Cookie(cookieName,stuCode+"#"+password);

        }
        cookie.setMaxAge(time);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
    }

    private static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null&& cookies.length>0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(cookieName)){
                    return cookie;
                }
            }
        }
        return null;
    }
}
