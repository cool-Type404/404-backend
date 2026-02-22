FROM eclipse-temurin:17-jdk
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app.jar"]
# RDS 마이그레이션 확인을 위한 빌드 재실행 주석