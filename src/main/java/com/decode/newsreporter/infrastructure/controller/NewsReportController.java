package com.decode.newsreporter.infrastructure.controller;
import com.decode.newsreporter.domain.entity.News;
import com.decode.newsreporter.domain.repository.NewsRepository;
import com.decode.newsreporter.infrastructure.service.GenerateReportService;
import com.decode.newsreporter.infrastructure.service.UnableToGenerateReportException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@Controller
public class NewsReportController {

    private final NewsRepository newsRepository;
    private final GenerateReportService generateReportService;
    private static final String BASE_URL="/api/v1/news/report";
    private static final String PAGE_TITLE="News Report";

    public NewsReportController(NewsRepository newsRepository, GenerateReportService generateReportService) {
        this.newsRepository = newsRepository;
        this.generateReportService = generateReportService;
    }

    @GetMapping(BASE_URL)
    public String getNewsHtmlView(Model model) {
        List<News> reportData = newsRepository.getAllNews();
        model.addAttribute("reportData", reportData);
        model.addAttribute("title", PAGE_TITLE);
        return "report";
    }

    @GetMapping(BASE_URL + "/download")
    public String getReportFile(HttpServletRequest request, Model model) throws UnableToGenerateReportException {
        try {
            String fileLink = generateReportService.getFileLink(request);
            model.addAttribute("fileLink", fileLink);
        } catch (UnableToGenerateReportException e) {
            throw new UnableToGenerateReportException();
        }
        return "report_download";
    }
}
