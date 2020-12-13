package com.britosw.producerapi.exception;

import com.britosw.producerapi.message.ApplicationMessage;
import com.britosw.producerapi.message.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import util.LoggerUtil;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@Component
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, List<ApplicationMessage>> handleConstraintViolationException(ConstraintViolationException ex) {
        List<ApplicationMessage> messages = new ArrayList<>();
        for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {
            messages.add(ApplicationMessage.parse(cv.getMessage()));
        }
        return errors(messages);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, List<ApplicationMessage>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ApplicationMessage> messages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> messages.add(ApplicationMessage.parse(Objects.requireNonNull(error.getDefaultMessage()))));
        return errors(messages);
    }

    /* path parameter type validation */
    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, List<ApplicationMessage>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return errors(Collections.singletonList(ApplicationMessage.parse(
                String.format(Messages.TYPE_MISMATCH_REQUEST, ex.getName(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName()))));
    }

    /* path parameter not found */
    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, List<ApplicationMessage>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return errors(Collections.singletonList(ApplicationMessage.parse(
                String.format(Messages.REQUEST_METHOD_NOT_SUPPORTED, ex.getMethod()))));
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, List<ApplicationMessage>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return errors(ApplicationMessage.parse(Messages.MALFORMED_JSON_REQUEST));
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Map<String, List<ApplicationMessage>> handleApplicationException(ApplicationException ex) {
        LoggerUtil.logError(log, ex);
        return errors(ApplicationMessage.parse(ex.getMessage()));
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, List<ApplicationMessage>> handleApplicationInternalException(ApplicationInternalException ex) {
        LoggerUtil.logError(log, ex);
        return errors(ApplicationMessage.parse(ex.getMessage()));
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, List<ApplicationMessage>> handleException(Exception exception) {
        LoggerUtil.logError(log, exception);
        return errors(ApplicationMessage.parse(Messages.DEFAULT_ERROR));
    }

    private Map<String, List<ApplicationMessage>> errors(List<ApplicationMessage> messages) {
        return Collections.singletonMap("errors", messages);
    }

    private Map<String, List<ApplicationMessage>> errors(ApplicationMessage message) {
        return Collections.singletonMap("errors", Collections.singletonList(message));
    }
}
