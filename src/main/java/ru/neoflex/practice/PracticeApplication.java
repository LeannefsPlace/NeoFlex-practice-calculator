package ru.neoflex.practice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "ru.neoflex.practice")
@EnableJpaRepositories("ru.neoflex.practice")
@OpenAPIDefinition(
        info = @Info(
                title="NeoFlex practice calculator project", description = "Practice project made by Ivan Yakimov", contact = @Contact(name = "Ivan Yakimov", email = "ya4ki@yandex.ru"), version = "1.0.0"
        )
)
public class PracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class, args);
    }


}

