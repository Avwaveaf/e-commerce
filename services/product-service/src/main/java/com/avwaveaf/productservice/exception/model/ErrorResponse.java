package com.avwaveaf.productservice.exception.model;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
