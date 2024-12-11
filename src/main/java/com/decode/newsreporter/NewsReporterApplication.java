package com.decode.newsreporter;
import com.decode.newsreporter.Domain.ValueObject.NewsName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class NewsReporterApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewsReporterApplication.class, args);
        NewsName newsName = new NewsName("sdfsdfsdf");
        log.info("Application has started");
    }
}


