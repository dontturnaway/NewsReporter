package com.decode.newsreporter.Infrastructure.Service;

import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Domain.Repository.NewsRepositoryInterface;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class GenerateReportService {

    private final NewsRepositoryInterface newsRepository;
    private final TemplateEngine templateEngine;

    public GenerateReportService(NewsRepositoryInterface newsRepository, TemplateEngine templateEngine) {
        this.newsRepository = newsRepository;
        this.templateEngine = templateEngine;
    }

    public String getFileLink(HttpServletRequest request) throws IOException {
        // Define the path to save the HTML report
        String reportsDirectoryPath = "src/main/resources/static/reports";
        String fileName = "newsreport.html";
        File reportsDirectory = new File(reportsDirectoryPath);

        // Ensure the directory exists
        if (!reportsDirectory.exists()) {
            boolean created = reportsDirectory.mkdirs();
            if (!created) {
                throw new IOException("Failed to create directory " + reportsDirectoryPath);
            }
        }

        // Generate the HTML content
        Context context = new Context();
        List<News> reportData = newsRepository.getAllNews();
        context.setVariable("reportData", reportData);
        context.setVariable("title", "News Report");
        String htmlContent = templateEngine.process("report", context);

        // Save the HTML content to a file
        File reportFile = new File(reportsDirectory, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile))) {
            writer.write(htmlContent);
        }

        // Construct the full URL for the report
        String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String fileLink = baseUrl + "/reports/" + fileName;
        return fileLink;
    }
}
