# Используем официальный образ Maven для сборки
FROM maven:3.9-eclipse-temurin-17 AS build

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем pom.xml
COPY pom.xml .

# Копируем исходный код
COPY src ./src

# Собираем приложение
RUN mvn clean package -DskipTests

# Используем официальный образ Java для запуска
FROM eclipse-temurin:17-jre-alpine

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем собранный JAR файл из предыдущего этапа
COPY --from=build /app/target/*.jar app.jar

# Открываем порт
EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"] 