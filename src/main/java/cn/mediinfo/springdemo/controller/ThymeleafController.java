package cn.mediinfo.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * see https://localhost:8000/mediinfo-springdemo/index
 */
@Controller
public class ThymeleafController  {
    @RequestMapping("/index")
    public ModelAndView Index() {
        String message = "Hello, Thymeleaf!";
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message", message);
        return mav;
    }
}
