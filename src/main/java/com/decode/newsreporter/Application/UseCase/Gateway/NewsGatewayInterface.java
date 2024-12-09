package com.decode.newsreporter.Application.UseCase.Gateway;

public interface NewsGatewayInterface {
    String getNewsFromURL(String url) throws CanGetRemoteDataFromURLException;
}
