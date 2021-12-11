package com.example.multimodule.service.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder {

    public static Response success(String uri){
        return Response.builder()
                .data(HttpStatus.OK)
                .Uri(uri)
                .build();
    }

    public static Response success(Object data, String uri){
        return Response.builder()
                .data(data)
                .Uri(uri)
                .build();
    }

    public static Response successCreated(Object data, String uri) {
        return Response.builder()
                .data(data)
                .Uri(uri)
                .build();
    }

    public static Response failed(Object data, String uri){
        return Response.builder()
                .data(data)
                .Uri(uri)
                .build();
    }
}
