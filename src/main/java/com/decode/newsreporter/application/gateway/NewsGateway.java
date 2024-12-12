package com.decode.newsreporter.application.gateway;

public interface NewsGateway {
    NewsGatewayResponseDTO getNewsFromURL(NewsGatewayRequestDTO dto) throws CanGetRemoteDataFromURLException;
}
