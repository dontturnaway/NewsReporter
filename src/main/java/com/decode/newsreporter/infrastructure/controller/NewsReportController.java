package com.decode.newsreporter.infrastructure.controller;
import com.decode.newsreporter.domain.service.NewsService;
import com.decode.newsreporter.infrastructure.dto.NewsDTO;
import com.decode.newsreporter.infrastructure.service.ReportGenerationServiceImpl;
import com.decode.newsreporter.domain.service.report_generation.UnableToGenerateReportException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@Controller
public class NewsReportController {

//    private final NewsService newsService;
//    private final ReportGenerationServiceImpl reportGenerationServiceImpl;
//    private static final String BASE_URL="/api/v1/news/report";
//    private static final String PAGE_TITLE="News Report";
//
//    public NewsReportController(NewsService newsService, ReportGenerationServiceImpl reportGenerationServiceImpl) {
//        this.newsService = newsService;
//        this.reportGenerationServiceImpl = reportGenerationServiceImpl;
//    }
//
//    @GetMapping(BASE_URL)
//    public String getNewsHtmlView(Model model) {
//        List<NewsDTO> reportData = newsService.getAllNews();
//        model.addAttribute("reportData", reportData);
//        model.addAttribute("title", PAGE_TITLE);
//        return "report";
//    }
//
//    @GetMapping(BASE_URL + "/download")
//    public String getReportFile(HttpServletRequest request, Model model) throws UnableToGenerateReportException {
//        try {
//            String fileLink = reportGenerationServiceImpl.getFileLink(request);
//            model.addAttribute("fileLink", fileLink);
//        } catch (UnableToGenerateReportException e) {
//            throw new UnableToGenerateReportException();
//        }
//        return "report_download";
//    }
//
//

}
