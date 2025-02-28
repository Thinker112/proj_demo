FROM amazoncorretto:11
# 设置工作目录
WORKDIR /app

# 将 JAR 文件复制到容器中
COPY target/asynchronous_demo-*.jar app.jar

# 暴露应用端口
EXPOSE 8080

# 运行应用
ENTRYPOINT ["java", "-jar", "app.jar"]