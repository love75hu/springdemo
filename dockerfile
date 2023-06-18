# 第一阶段：构建基础镜像
FROM openjdk:17-jdk-alpine as base

# 定义暴露端口和工作目录
EXPOSE 80
EXPOSE 443
WORKDIR /app

# 第二阶段：构建 Spring Boot 应用程序
FROM gradle:7.2.0-jdk17 AS build

# 将应用程序源码复制到镜像中，并执行 Gradle 编译
WORKDIR /app
COPY . .
RUN gradle -b build.gradle -x test clean build

# 第三阶段：创建一个新的容器，运行 Spring Boot 应用程序
FROM base as final

# 拷贝编译生成的 jar 文件和资源文件到容器中，并设置启动命令
WORKDIR /app
COPY --from=build /app/build/libs/mediinfo-spring-demo-0.0.1-SNAPSHOT.jar .
COPY --from=build /app/build/resources/main application.yaml
ENTRYPOINT ["java", "-Dfile.encoding=utf-8", "-Dspring.config.location=/app/application.yaml", "-jar", "mediinfo-spring-demo-0.0.1-SNAPSHOT.jar"]
