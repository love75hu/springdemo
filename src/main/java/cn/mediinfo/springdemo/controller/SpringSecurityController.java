package cn.mediinfo.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * see https://localhost:8000/mediinfo-springdemo/login
 */
@Controller
public class SpringSecurityController {
    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}
