package com.decode.newsreporter.infrastructure.controller;

import com.decode.newsreporter.application.gateway.CanGetRemoteDataFromURLException;
import com.decode.newsreporter.application.usecase.get_all_news_list.GetAllNewsListUsecase;
import com.decode.newsreporter.application.usecase.get_news_by_id.GetNewsByIDRequest;
import com.decode.newsreporter.application.usecase.get_news_by_id.GetNewsByIDUsecase;
import com.decode.newsreporter.application.usecase.get_news_report.GenerateNewsReportUsecase;
import com.decode.newsreporter.application.usecase.get_news_report.GetNewsReportRequest;
import com.decode.newsreporter.application.usecase.get_news_report.GetNewsReportResponse;
import com.decode.newsreporter.application.usecase.submit_news.SubmitNewsRequestDTO;
import com.decode.newsreporter.application.usecase.submit_news.SubmitNewsResponseDTO;
import com.decode.newsreporter.application.usecase.submit_news.SubmitNewsUsecase;
import com.decode.newsreporter.infrastructure.service.news_parser.parsing_strategy.CantParseNewsException;
import com.decode.newsreporter.infrastructure.service.WrongUrlProvided;
import com.decode.newsreporter.application.service.report_generation.UnableToGenerateReportException;
import com.decode.newsreporter.infrastructure.entity.NewsDTO;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class NewsController {

    private final SubmitNewsUsecase submitNewsUsecase;
    private final GenerateNewsReportUsecase generateNewsReportUsecase;
    private final GetAllNewsListUsecase getAllNewsListUsecase;
    private final GetNewsByIDUsecase getNewsByIDUsecase;

    private static final String BASE_NEWS_URL="/api/v1/news";

    public NewsController(SubmitNewsUsecase submitNewsUsecase,
                          GenerateNewsReportUsecase generateNewsReportUsecase,
                          GetAllNewsListUsecase getAllNewsListUsecase,
                          GetNewsByIDUsecase getNewsByIDUsecase
    ) {
        this.submitNewsUsecase = submitNewsUsecase;
        this.generateNewsReportUsecase = generateNewsReportUsecase;
        this.getAllNewsListUsecase = getAllNewsListUsecase;
        this.getNewsByIDUsecase = getNewsByIDUsecase;
    }

    @GetMapping(value=BASE_NEWS_URL, produces = APPLICATION_JSON_VALUE)
    public List<NewsDTO> getAllNews() {
        return getAllNewsListUsecase.getNewsList().newsDTOList();
    }

    @GetMapping(value=BASE_NEWS_URL + "/{newsId}", produces = APPLICATION_JSON_VALUE)
    public NewsDTO getNewsById(GetNewsByIDRequest getNewsByIDRequest) throws WrongNewsIdProvided {
        if (getNewsByIDRequest == null || getNewsByIDRequest.newsId() == null) {
            throw new WrongNewsIdProvided();
        }
        return getNewsByIDUsecase.getNewsList(getNewsByIDRequest).newsDTO();
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
    public GetNewsReportResponse generateNewsReport(@RequestBody GetNewsReportRequest getNewsReportRequest) throws
                                            WrongNewsIdProvided,
                                            UnableToGenerateReportException {

        if (getNewsReportRequest ==null || getNewsReportRequest.newsListIds().isEmpty())
            throw new WrongNewsIdProvided();

        return generateNewsReportUsecase.getReport(getNewsReportRequest);
    }

}
