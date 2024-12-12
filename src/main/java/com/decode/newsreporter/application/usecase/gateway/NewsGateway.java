package com.decode.newsreporter.application.usecase.gateway;

public interface NewsGateway {
    NewsGatewayResponseDTO getNewsFromURL(NewsGatewayRequestDTO dto) throws CanGetRemoteDataFromURLException;
}
