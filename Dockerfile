# Sử dụng hình ảnh JDK chính thức làm nền
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép file JAR vào container
COPY target/nexis-0.0.1-SNAPSHOT.jar app.jar
RUN chmod 777 /app/app.jar
# Expose cổng mà ứng dụng Spring Boot sẽ chạy
EXPOSE 8080

ENTRYPOINT  ["java", "-jar", "/app/app.jar"]
