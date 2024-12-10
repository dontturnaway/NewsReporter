package com.decode.newsreporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class NewsReporterApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsReporterApplication.class, args);
        log.info("Application has started");
    }

}


