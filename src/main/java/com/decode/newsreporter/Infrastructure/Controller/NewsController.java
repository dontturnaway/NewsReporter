package com.decode.newsreporter.Infrastructure.Controller;

import com.decode.newsreporter.Application.UseCase.Gateway.CanGetRemoteDataFromURLException;
import com.decode.newsreporter.Application.UseCase.SubmitNews.SubmitNewsRequest;
import com.decode.newsreporter.Application.UseCase.SubmitNews.SubmitNewsResponse;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.CantParseNewsException;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.WrongUrlProvided;
import com.decode.newsreporter.Infrastructure.Command.SubmitNewsCommand;
import com.decode.newsreporter.Infrastructure.Controller.Exceptions.WrongNewsId;
import com.decode.newsreporter.Infrastructure.Entity.NewsDTO;
import com.decode.newsreporter.Infrastructure.Service.NewsService;
import com.decode.newsreporter.Infrastructure.Service.NewsServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class NewsController {

    private final NewsService newsService;
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
    public SubmitNewsResponse postNews(@RequestBody SubmitNewsRequest submitNewsRequest) throws
                                                                        CantParseNewsException,
                                                                        CanGetRemoteDataFromURLException,
                                                                        WrongUrlProvided {
        if (submitNewsRequest.URL() ==null ||  submitNewsRequest.URL().isEmpty())
            throw new WrongUrlProvided();

        return submitNewsCommand.execute(submitNewsRequest);
    }
}
