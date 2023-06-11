package cn.mediinfo.springdemo.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor //@RequiredArgsConstructor 是 Lombok 库提供的一个注解，可以自动生成一个包含所有 final 字段的构造函数
public class SessionController {
    private final HttpSession httpSession;

    /**
     * 登录页面
     * link https://localhost:8000/mediinfo-springdemo/session/login
     *
     * @return
     */
    @ResponseBody //@ResponseBody 是 Spring MVC 中的一个注解，用于指定方法返回的结果直接作为 HTTP 响应正文返回，而不是视图名称或视图解析器解析后的视图。
    @RequestMapping("session/login")
    public String Login() {
        return "登录页面";
    }

    /**
     * 首页
     * link https://localhost:8000/mediinfo-springdemo/session/index
     *
     * @return
     */
    @ResponseBody //@ResponseBody 是 Spring MVC 中的一个注解，用于指定方法返回的结果直接作为 HTTP 响应正文返回，而不是视图名称或视图解析器解析后的视图。
    @RequestMapping("/session/index")
    public String Index() {
        return "首页";
    }

    /**
     * 登录请求
     * link https://localhost:8000/mediinfo-springdemo/session/submit?username=张三
     *
     * @param username
     * @return
     */
    @RequestMapping("/session/submit")
    public String loginSubmit(String username) {
        if (StringUtils.hasText(username)) {
            //登录后，在redis可以看到session会话的数据存储在里面
            httpSession.setAttribute("username", username);
            return "redirect:/session/index";
        }
        return "redirect:/session/login";
    }

    /**
     * 退出
     * link https://localhost:8000/mediinfo-springdemo/session/logout
     *
     * @return
     */
    @RequestMapping("/session/logout")
    public String logout() {
        //退出后，在redis可以看到session会话的数据已经delete
        httpSession.invalidate(); //使会话失效，清除绑定
        return "redirect:/session/login";
    }
}
