package com.decode.newsreporter.infrastructure.controller;

import com.decode.newsreporter.application.usecase.gateway.CanGetRemoteDataFromURLException;
import com.decode.newsreporter.application.usecase.submitNews.SubmitNewsRequestDTO;
import com.decode.newsreporter.application.usecase.submitNews.SubmitNewsResponseDTO;
import com.decode.newsreporter.domain.service.parsing_strategy.CantParseNewsException;
import com.decode.newsreporter.domain.service.parsing_strategy.WrongUrlProvided;
import com.decode.newsreporter.infrastructure.command.SubmitNewsCommand;
import com.decode.newsreporter.infrastructure.dto.NewsDTO;
import com.decode.newsreporter.infrastructure.service.NewsServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class NewsController {

    private final NewsServiceImpl newsService;
    private final SubmitNewsCommand submitNewsCommand;
    private static final String BASE_NEWS_URL="/api/v1/news";

    public NewsController(SubmitNewsCommand submitNewsCommand, NewsServiceImpl newsService) {
        this.submitNewsCommand = submitNewsCommand;
        this.newsService = newsService;
    }

    @GetMapping(value=BASE_NEWS_URL, produces = APPLICATION_JSON_VALUE)
    public List<NewsDTO> getAllNews() {
        return newsService.getAllNews();
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

        return submitNewsCommand.execute(submitNewsRequestDTO);
    }
}
