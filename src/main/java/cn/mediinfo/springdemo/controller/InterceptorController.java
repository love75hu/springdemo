package cn.mediinfo.springdemo.controller;

import cn.mediinfo.springdemo.response.MsfResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;


/**
 * 拦截器测试控制器
 */
@RestController
@Slf4j
@RequestMapping("api/v1/Interceptor")
public class InterceptorController {

    /**
     * 传入参数不过滤，不传则过滤
     *
     * @return
     * @throws Exception
     */
    @Operation(summary = "拦截器测试控制器")
    @GetMapping("get")
    public MsfResponse<String> Get(@Parameter(allowEmptyValue = true ) String userid) {
        if (!StringUtils.isEmpty(userid)) {
            // 获取ServletRequestAttributes对象
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            // 获取HttpServletRequest对象
            attributes.getRequest().getSession().setAttribute("userid", "谭洪军ID");
        }
        return MsfResponse.success(userid);
    }
}
