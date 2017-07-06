package ua.kiev.allexb.mvc.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ScopeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScopeController.class);

    @RequestMapping(value = "/scopeSession", method = RequestMethod.GET)
    public ModelAndView scopeExample(HttpSession session) {
        LOGGER.info("ScopeController scopeSession() is called");
        session.setMaxInactiveInterval(3600);
        session.setAttribute("sessionObject", "Value is session object");
        return new ModelAndView("/scope/scope");
    }

    @RequestMapping(value = "/invalidateSession", method=RequestMethod.GET)
    public ModelAndView invalidateSession(HttpSession session) {
        LOGGER.info("ScopeController invalidate is called");
        session.invalidate();
        return new ModelAndView("/scope/scope");
    }

    @RequestMapping(value = "/scopeRequest", method=RequestMethod.GET)
    public ModelAndView requestScopeExample(HttpServletRequest request) {
        LOGGER.info("ScopeController requestScopeExample is called");
        request.setAttribute("requestObject", "This is request object");
        return new ModelAndView("/scope/scope");
    }

}
