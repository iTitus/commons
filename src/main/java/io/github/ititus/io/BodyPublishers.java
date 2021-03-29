package io.github.ititus.io;

import java.net.URLEncoder;
import java.net.http.HttpRequest.BodyPublisher;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static java.net.http.HttpRequest.BodyPublishers.noBody;
import static java.net.http.HttpRequest.BodyPublishers.ofString;

public final class BodyPublishers {

    private BodyPublishers() {
    }

    public static BodyPublisher ofFormData(Map<String, String> data) {
        if (data == null || data.isEmpty()) {
            return noBody();
        }

        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }

            builder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }

        return ofString(builder.toString());
    }
}
