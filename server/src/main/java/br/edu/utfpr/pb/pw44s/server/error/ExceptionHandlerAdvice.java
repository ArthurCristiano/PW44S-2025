package br.edu.utfpr.pb.pw44s.server.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    // Trata as exceções que irão ocorrer devido a anotação @Valid
    @ExceptionHandler({MethodArgumentNotValidException.class})
    // Irá retornar como HttpStatus 400 - Bad Request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerValidationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        //Recupera a lista de erros
        BindingResult result = exception.getBindingResult();
        Map<String, String> validationErrors = new HashMap<>();
        //Cria uma lista com os erros
        for (FieldError fieldError : result.getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        //Retorna uma json com um objeto do tipo ApiError com os errors durante a validação dos dados enviados.
        return new ApiError(HttpStatus.BAD_REQUEST.value(), "Validation error!",
                request.getServletPath(), validationErrors);
    }

    @ExceptionHandler({IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerValidationException(IllegalStateException exception, HttpServletRequest request) {
        return new ApiError(HttpStatus.BAD_REQUEST.value(), "Validation error!",
                request.getServletPath(), null);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerValidationException(HttpMessageNotReadableException exception, HttpServletRequest request) {
        return new ApiError(HttpStatus.BAD_REQUEST.value(), "Validation error!",
                request.getServletPath(), null);
    }
}