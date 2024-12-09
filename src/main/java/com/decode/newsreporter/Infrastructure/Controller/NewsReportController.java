package com.decode.newsreporter.Infrastructure.Controller;
import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Domain.Repository.NewsRepositoryInterface;
import com.decode.newsreporter.Infrastructure.Service.GenerateReportService;
import com.decode.newsreporter.Infrastructure.Service.UnableToGenerateReportException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
public class NewsReportController {

    private final NewsRepositoryInterface newsRepository;
    private final GenerateReportService generateReportService;
    private static final String BASE_URL="/api/v1/news/report";
    private static final String PAGE_TITLE="News Report";

    public NewsReportController(NewsRepositoryInterface newsRepository, GenerateReportService generateReportService) {
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
        String fileLink = null;
        try {
            fileLink = generateReportService.getFileLink(request, model);
        } catch (IOException e) {
            throw new UnableToGenerateReportException();
        }
        if (fileLink == null) {
            throw new UnableToGenerateReportException();
        };

        model.addAttribute("fileLink", fileLink);
        return "report_download";
    }
}
