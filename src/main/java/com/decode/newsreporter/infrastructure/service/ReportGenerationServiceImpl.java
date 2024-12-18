package com.decode.newsreporter.infrastructure.service;

import com.decode.newsreporter.application.service.report_generation.ReportGenerationService;
import com.decode.newsreporter.application.service.report_generation.ReportLinkRequestDTO;
import com.decode.newsreporter.application.service.report_generation.ReportLinkResponseDTO;
import com.decode.newsreporter.application.service.report_generation.UnableToGenerateReportException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

@Slf4j
@Service
public class ReportGenerationServiceImpl implements ReportGenerationService {

    @Value("${app.base.url}")
    private final String APP_BASE_URL;
    private final TemplateEngine templateEngine;
    private static final String REPORT_DIRECTORY_PATH = "files/reports/";
    private static final String REPORT_URL_PATH = "/reports/";


    public ReportGenerationServiceImpl(@Value("${app.base.url}") String baseUrl, TemplateEngine templateEngine) {
        APP_BASE_URL = baseUrl;
        this.templateEngine = templateEngine;
    }

    @Override
    public ReportLinkResponseDTO getGeneratedReportLink(ReportLinkRequestDTO reportLinkRequestDTO) throws UnableToGenerateReportException {

        // Generate the HTML content
        Context context = new Context();
        context.setVariable("reportData", reportLinkRequestDTO.newsList());
        context.setVariable("title", "News Report");
        String htmlContent = templateEngine.process("report", context);

        // Construct the full URL for the report
        String reportFileName = this.writeReportFile(htmlContent);
        String fileLink = APP_BASE_URL + REPORT_URL_PATH + reportFileName;
        return new ReportLinkResponseDTO(fileLink);
    }

    private String writeReportFile(String htmlContent) throws UnableToGenerateReportException {

        File reportsDirectory = new File(REPORT_DIRECTORY_PATH);
        String fileName = this.generateReportName();

        // Ensure the directory exists
        if (!reportsDirectory.exists()) {
            boolean created = reportsDirectory.mkdirs();
            if (!created) {
                log.error("Failed to create directory: {}", REPORT_DIRECTORY_PATH);
                throw new UnableToGenerateReportException();
            }
        }

        // Save the HTML content to a file
        File reportFile = new File(REPORT_DIRECTORY_PATH, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile))) {
            writer.write(htmlContent);
        } catch (IOException e) {
            throw new UnableToGenerateReportException();
        }

        return fileName;
    }

    private String generateReportName() {
        Format formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentTime = formatter.format(new java.util.Date());
        return "newsreport_" + currentTime + ".html";
    }

}
