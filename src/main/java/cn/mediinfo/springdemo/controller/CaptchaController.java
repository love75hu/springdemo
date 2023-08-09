package cn.mediinfo.springdemo.controller;

import com.google.code.kaptcha.Producer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * 图形验证码
 */
@RestController
@Slf4j
@RequestMapping("api/v1/Captcha")
@RequiredArgsConstructor
public class CaptchaController {
    private final Producer producer;

    @Operation(summary = "获取图形验证码")
    @GetMapping("/captcha")
    public void generateCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        response.setContentType("image/jpeg");
        // 生成验证码文本
        String text = producer.createText();

        //将生成的验证码存入session
        session.setAttribute("captcha",text);

        // 创建验证码图片
        var image = producer.createImage(text);

        // 将验证码图片写入响应输出流
        ImageIO.write(image, "jpeg", response.getOutputStream());
    }

    @Operation(summary = "验证图形验证码")
    @PostMapping("/verify")
    public String verifyCaptcha(
            @RequestParam("captcha") @Parameter(description = "captcha") @NotNull @Size(min = 4, max = 4) String captcha,
            HttpSession session) {
        // 获取用户输入的验证码
        String userInput = captcha.trim();

        // 从 session 中获取生成的验证码
        String generatedCaptcha = (String) session.getAttribute("captcha");

        // 清除 session 中的验证码
        session.removeAttribute("captcha");

        // 验证用户输入的验证码是否正确
        if (userInput.equalsIgnoreCase(generatedCaptcha)) {
            // 验证通过，执行相应的逻辑
            return "验证码验证通过";
        } else {
            // 验证失败，返回错误信息或执行相应的处理
            return "验证码验证失败";
        }
    }
}
