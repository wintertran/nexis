# Sử dụng hình ảnh JDK chính thức làm nền
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép file JAR vào container
COPY ./target/nexis-0.0.1-SNAPSHOT.jar ./nexis-0.0.1-SNAPSHOT.jar

# Expose cổng mà ứng dụng Spring Boot sẽ chạy
EXPOSE 8080

# Lệnh để chạy ứng dụng khi container khởi động
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "./app.jar"]
EXPOSE 5005
