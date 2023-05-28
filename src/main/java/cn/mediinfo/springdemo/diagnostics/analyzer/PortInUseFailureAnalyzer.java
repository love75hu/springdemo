package cn.mediinfo.springdemo.diagnostics.analyzer;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.web.server.PortInUseException;

/**
 * 自定义启动失败分析器
 */
public class PortInUseFailureAnalyzer extends AbstractFailureAnalyzer<PortInUseException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, PortInUseException cause) {
        return new FailureAnalysis("Web server 启动失败. 端口 " + cause.getPort() + " 已经被占用.",
                "请检查端口 " + cause.getPort() + " 被哪个程序占用用了或者强制杀掉进程",
                cause);
    }
}
