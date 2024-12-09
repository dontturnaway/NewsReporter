package com.decode.newsreporter.Infrastructure.Repository.Gateway;
import com.decode.newsreporter.Application.UseCase.Gateway.CanGetRemoteDataFromURLException;
import com.decode.newsreporter.Application.UseCase.Gateway.NewsGatewayInterface;
import io.netty.handler.logging.LogLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

@Component
@Slf4j
public class NewsGateway implements NewsGatewayInterface {

    @Override
    public String getNewsFromURL(String url) throws CanGetRemoteDataFromURLException {

        WebClient client = getWebClient();

        String result = null;
        try {
            result = client
                    //Increase buffer for load large pages
                    .mutate()
                    .codecs(configurer -> configurer
                            .defaultCodecs()
                            .maxInMemorySize(16 * 1024 * 1024))
                    .build()
                    .get()
                    .uri(url)
                    .headers(httpHeaders -> {
                                httpHeaders.set("Accept-Language", "en-US,en;q=0.5");
                                httpHeaders.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
                                httpHeaders.set("Referer", url); // Optional
                            }
                    )
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("Unable to finish web request with status code: {}", e.getStatusCode());
            log.error("Unable to finish web request with status text:  {}", e.getStatusText());
            log.error("Unable to finish web request with response body:  {}", e.getMessage());
            log.error("Unable to finish web request with response body:  {}", e.getResponseBodyAsString());
            throw new CanGetRemoteDataFromURLException();
        } catch (Exception e) {
            log.error("Unable to finish web request with general exception:  {}", e.getMessage());
            throw new CanGetRemoteDataFromURLException();
        }
    return result;
    }

    private static WebClient getWebClient() {

        HttpClient httpClient = HttpClient
                .create()
                .followRedirect(true)
                .compress(true)
                .wiretap("reactor.netty.http.client.HttpClient",
                        LogLevel.DEBUG,
                        AdvancedByteBufFormat.TEXTUAL);

        return WebClient
                .builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

}
