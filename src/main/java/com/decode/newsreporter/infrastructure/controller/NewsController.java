package com.decode.newsreporter.infrastructure.controller;

import com.decode.newsreporter.application.gateway.CanGetRemoteDataFromURLException;
import com.decode.newsreporter.application.usecase.get_news_list.GetAllNewsListUsecase;
import com.decode.newsreporter.application.usecase.get_news_report.GenerateNewsReportUsecase;
import com.decode.newsreporter.application.usecase.get_news_report.GetNewsReportRequest;
import com.decode.newsreporter.application.usecase.get_news_report.GetNewsReportResponse;
import com.decode.newsreporter.application.usecase.submit_news.SubmitNewsRequestDTO;
import com.decode.newsreporter.application.usecase.submit_news.SubmitNewsResponseDTO;
import com.decode.newsreporter.application.usecase.submit_news.SubmitNewsUsecase;
import com.decode.newsreporter.domain.service.news_parser.parsing_strategy.CantParseNewsException;
import com.decode.newsreporter.domain.service.news_parser.parsing_strategy.WrongUrlProvided;
import com.decode.newsreporter.domain.service.report_generation.UnableToGenerateReportException;
import com.decode.newsreporter.infrastructure.entity.NewsDTO;
import com.decode.newsreporter.infrastructure.service.NewsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class NewsController {

    private final NewsServiceImpl newsService;
    private final SubmitNewsUsecase submitNewsUsecase;
    private final GenerateNewsReportUsecase generateNewsReportUsecase;
    private final GetAllNewsListUsecase getAllNewsListUsecase;

    private static final String BASE_NEWS_URL="/api/v1/news";

    public NewsController(NewsServiceImpl newsService,
                          SubmitNewsUsecase submitNewsUsecase,
                          GenerateNewsReportUsecase generateNewsReportUsecase,
                          GetAllNewsListUsecase getAllNewsListUsecase) {
        this.newsService = newsService;
        this.submitNewsUsecase = submitNewsUsecase;
        this.generateNewsReportUsecase = generateNewsReportUsecase;
        this.getAllNewsListUsecase = getAllNewsListUsecase;
    }

    @GetMapping(value=BASE_NEWS_URL, produces = APPLICATION_JSON_VALUE)
    public List<NewsDTO> getAllNews() {
        return getAllNewsListUsecase.getNewsList().newsDTOList();
    }

    @GetMapping(value=BASE_NEWS_URL + "/{newsId}", produces = APPLICATION_JSON_VALUE)
    public NewsDTO getNewsById(@PathVariable Long newsId) throws WrongNewsId {
        if (newsId == null)
            throw new WrongNewsId();
        return newsService.getNewsById(newsId);
    }

    @PostMapping(value = BASE_NEWS_URL, consumes = APPLICATION_JSON_VALUE)
    public SubmitNewsResponseDTO postNews(@RequestBody SubmitNewsRequestDTO submitNewsRequestDTO) throws
                                                                        CantParseNewsException,
                                                                        CanGetRemoteDataFromURLException,
                                                                        WrongUrlProvided {
        if (submitNewsRequestDTO.URL() ==null ||  submitNewsRequestDTO.URL().isEmpty())
            throw new WrongUrlProvided();

        return submitNewsUsecase.submitNews(submitNewsRequestDTO);
    }

    @PostMapping(value = BASE_NEWS_URL + "/report", consumes = APPLICATION_JSON_VALUE)
    public GetNewsReportResponse generateNewsReport(@RequestBody List<Long> ids, HttpServletRequest request) throws
            WrongUrlProvided, WrongNewsId, UnableToGenerateReportException {
        if (ids ==null || ids.isEmpty())
            throw new WrongUrlProvided();

        String requestUrl = request.getRequestURL().toString();
        GetNewsReportRequest getNewsReportRequest = new GetNewsReportRequest(ids, requestUrl);
        return generateNewsReportUsecase.getReport(getNewsReportRequest);
    }

}
