package com.example.multimodule.service.helpers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private Object data;
    private String Uri;
}
