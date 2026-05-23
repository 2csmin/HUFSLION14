package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "controller",
        "service",
        "repository",
        "dto",
        "domain"
})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}