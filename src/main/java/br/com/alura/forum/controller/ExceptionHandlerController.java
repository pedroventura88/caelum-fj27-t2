package br.com.alura.forum.controller;

import br.com.alura.forum.dto.output.MessageCodeOutputDto;
import br.com.alura.forum.dto.output.ValidationErrorsOutputDto;
import br.com.alura.forum.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@AllArgsConstructor
@RestControllerAdvice
public class ExceptionHandlerController {

    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ValidationErrorsOutputDto handleValidationError(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();

        ValidationErrorsOutputDto validationErrors = new ValidationErrorsOutputDto();
        fieldErrors.forEach(error -> validationErrors.addFieldError(error.getField(), getErrorMessage(error)));
        globalErrors.forEach(error -> validationErrors.addGlobalError(getErrorMessage(error)));
        return validationErrors;
    }

    private	String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    public MessageCodeOutputDto handleNotFoundException(Exception exception, WebRequest request) {
        return MessageCodeOutputDto.of("Resource Not Found", HttpStatus.NOT_FOUND.value());
    }

}
