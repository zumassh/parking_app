# Используем базовый образ с Gradle и JDK 21 для сборки приложения
FROM gradle:8.4.0-jdk21 AS build

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем только файлы конфигурации Gradle, чтобы кешировать загрузку зависимостей
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Загружаем зависимости (это ускоряет последующую сборку)
RUN ./gradlew dependencies --no-daemon

# Копируем оставшиеся файлы проекта
COPY src ./src

# Собираем приложение
RUN ./gradlew clean bootJar --no-daemon

# Используем базовый образ с JDK 21 для запуска приложения
FROM openjdk:21-jdk-slim

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем jar файл из этапа сборки
COPY --from=build /app/build/libs/*.jar app.jar

# Указываем команду для запуска приложения
CMD ["java", "-jar", "app.jar"]