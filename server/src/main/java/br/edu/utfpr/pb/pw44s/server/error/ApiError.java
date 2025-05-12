package br.edu.utfpr.pb.pw44s.server.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ApiError {
    private Long timestamp = new Date().getTime();
    private int status;
    private String message;
    private String url;
    private Map<String, String> validationErrors;

    public ApiError(String message, String url, int status) {
        this.status = status;
        this.message = message;
        this.url = url;
    }

    public ApiError(int status, String message, String url, Map<String, String> validationErrors) {
        this.status = status;
        this.message = message;
        this.url = url;
        this.validationErrors = validationErrors;
    }
}