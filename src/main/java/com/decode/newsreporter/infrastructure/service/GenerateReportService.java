package com.decode.newsreporter.infrastructure.service;

import com.decode.newsreporter.domain.service.NewsService;
import com.decode.newsreporter.infrastructure.dto.NewsDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class GenerateReportService {

    private final NewsService newsService;
    private final TemplateEngine templateEngine;

    public GenerateReportService(NewsService newsService, TemplateEngine templateEngine) {
        this.newsService = newsService;
        this.templateEngine = templateEngine;
    }

    public String getFileLink(HttpServletRequest request) throws UnableToGenerateReportException {
        // Define the path to save the HTML report
        String reportsDirectoryPath = "src/main/resources/static/reports";
        String fileName = "newsreport.html";
        File reportsDirectory = new File(reportsDirectoryPath);

        // Ensure the directory exists
        if (!reportsDirectory.exists()) {
            boolean created = reportsDirectory.mkdirs();
            if (!created) {
                log.error("Failed to create directory: {}", reportsDirectoryPath);
                throw new UnableToGenerateReportException();
            }
        }

        // Generate the HTML content
        Context context = new Context();
        List<NewsDTO> reportData = newsService.getAllNews();
        context.setVariable("reportData", reportData);
        context.setVariable("title", "News Report");
        String htmlContent = templateEngine.process("report", context);

        // Save the HTML content to a file
        File reportFile = new File(reportsDirectory, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile))) {
            writer.write(htmlContent);
        } catch (IOException e) {
            throw new UnableToGenerateReportException();
        }

        // Construct the full URL for the report
        String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        return baseUrl + "/reports/" + fileName;
    }
}
