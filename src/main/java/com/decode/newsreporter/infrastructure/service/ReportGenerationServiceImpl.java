package com.decode.newsreporter.infrastructure.service;

import com.decode.newsreporter.domain.service.NewsService;
import com.decode.newsreporter.domain.service.report_generation.ReportGenerationService;
import com.decode.newsreporter.domain.service.report_generation.ReportLinkRequestDTO;
import com.decode.newsreporter.domain.service.report_generation.ReportLinkResponseDTO;
import com.decode.newsreporter.domain.service.report_generation.UnableToGenerateReportException;
import com.decode.newsreporter.infrastructure.dto.NewsDTO;
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
public class ReportGenerationServiceImpl implements ReportGenerationService {

    private final TemplateEngine templateEngine;

    public ReportGenerationServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public ReportLinkResponseDTO getFileLink(ReportLinkRequestDTO reportLinkRequestDTO) throws UnableToGenerateReportException {

        List<NewsDTO> newsListReportData =  reportLinkRequestDTO.newsList();
        String requestUrl = reportLinkRequestDTO.requestURL();

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
        context.setVariable("reportData", newsListReportData);
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
        String fileLink = requestUrl + "/reports/" + fileName;
        return new ReportLinkResponseDTO(fileLink);
    }

}
