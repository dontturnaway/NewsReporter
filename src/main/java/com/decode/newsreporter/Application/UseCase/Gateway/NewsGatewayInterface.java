package com.decode.newsreporter.Application.UseCase.Gateway;

import com.decode.newsreporter.Domain.ValueObject.URL;

public interface NewsGatewayInterface {
    String getNewsFromURL(URL url) throws CanGetRemoteDataFromURLException;
}
