package pl.allegro.zadanie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class AllegroApplication {

    public static void main(String[] args) {
        SpringApplication.run(AllegroApplication.class, args);
    }

}
