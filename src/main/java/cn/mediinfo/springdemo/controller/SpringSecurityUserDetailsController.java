package cn.mediinfo.springdemo.controller;


import cn.mediinfo.springdemo.response.MsfResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping({ "api/v1/SpringSecurityUserDetails"})
@RequiredArgsConstructor
public class SpringSecurityUserDetailsController {

    /**
     * 获取登录的用户信息方式1（通过注解）
     * @param userDetails
     * @return MsfResponse
     */
    @Operation(summary = "获取登录的用户信息方式1（通过注解@AuthenticationPrincipal）")
    @GetMapping("/UserDetails")
    public MsfResponse getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
         return MsfResponse.success(username);
    }

    /**
     * 获取登录的用户信息
     * @return MsfResponse
     */
    @Operation(summary = "获取登录的用户信息方式2（通过SecurityContextHolder）")
    @GetMapping("/UserDetails2")
    public MsfResponse getUserInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return MsfResponse.success(username);
    }
}
