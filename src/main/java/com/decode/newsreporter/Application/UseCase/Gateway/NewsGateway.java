package com.decode.newsreporter.Application.UseCase.Gateway;

public interface NewsGateway {
    NewsGatewayResponseDTO getNewsFromURL(NewsGatewayRequestDTO dto) throws CanGetRemoteDataFromURLException;
}
