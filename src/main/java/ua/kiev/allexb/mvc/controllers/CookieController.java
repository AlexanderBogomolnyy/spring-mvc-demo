package ua.kiev.allexb.mvc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CookieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieController.class);

    @RequestMapping(value = "/readCookie", method = RequestMethod.GET)
    public ModelAndView readCookieExample(@CookieValue(value = "cookieName", required = false) Cookie cookieName, HttpServletRequest request) {
        LOGGER.info("CookieController readCookieExample() is called");
        //you can use also " @CookieValue(value = "cookieName") String cookieName " >cookieName = Cookie.getName();
        String cookieValue = "cookie with name 'cookieName' is empty";
        if (cookieName != null) {
            cookieValue  = "Object: " + cookieName + ";<br/> Name: " + cookieName.getName() + ";<br/> Value: " + cookieName.getValue();
        }
        return new ModelAndView("/cookie/cookie", "cookieValueObj", cookieValue);
    }

    @RequestMapping(value = "/writeCookie", method=RequestMethod.GET)
    public String writeCookieExample (HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("CookieControllerExample writeCookieExample() is called");
        Cookie cookie = new Cookie("cookieName", request.getRequestURL().toString());
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        LOGGER.info("Object: " + cookie + "; Name: " + cookie.getName() + "; Value: " + cookie.getValue());
        return "/cookie/cookie";
    }

    @RequestMapping(value = "/readAllCookies", method=RequestMethod.GET)
    public ModelAndView readAllCookiesExample(HttpServletRequest request) {
        LOGGER.info("CookieControllerExample readAllCookiesExample() is called");
        Cookie[] cookies = request.getCookies();
        LOGGER.info("All Cookies in your browsers");
        StringBuilder cookiesStr = new StringBuilder();
        for(Cookie cookie : cookies){
            LOGGER.info(cookie.getName() + " : " + cookie.getValue());
            cookiesStr.append(cookie.getName())
                    .append(" : ")
                    .append(cookie.getValue())
                    .append(" : ")
                    .append(cookie)
                    .append("<br/>");
        }
        return new ModelAndView("/cookie/cookie", "cookieValueObj", cookiesStr.toString());
    }

}