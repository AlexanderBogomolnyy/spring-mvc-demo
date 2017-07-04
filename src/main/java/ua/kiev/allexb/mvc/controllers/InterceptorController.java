package ua.kiev.allexb.mvc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InterceptorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterceptorController.class);

    @RequestMapping(value = "/interceptorCall/subLevel", method = RequestMethod.GET)
    public ModelAndView interceptorCall() {
        LOGGER.info("interceptorCall() is called");
        return new ModelAndView("/index");
    }
}
