# Сборка JAR с помощью официального образа Gradle + JDK 21
FROM gradle:8.13-jdk21 AS builder
WORKDIR /app

# Копируем только build.gradle для кэширования зависимостей
COPY build.gradle .

# Скачиваем зависимости
RUN gradle dependencies --no-daemon

# Теперь копируем остальные файлы
COPY . .

# Собираем JAR
RUN gradle bootJar --no-daemon

# Запуск приложения
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]