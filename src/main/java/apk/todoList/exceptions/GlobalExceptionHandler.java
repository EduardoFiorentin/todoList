package apk.todoList.exceptions;

import apk.todoList.controller.dto.commom.ErrorField;
import apk.todoList.controller.dto.commom.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getFieldErrors();
        List<ErrorField> errorList = errors
                .stream()
                .map(err -> new ErrorField(err.getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseError(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            "Erro de validação!",
            errorList
        );
    }

    @ExceptionHandler(InvalidFieldException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseError handleInvalidFieldException(InvalidFieldException e) {
        return new ResponseError(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação.",
                List.of(new ErrorField(e.getField(), e.getMessage()))
        );
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handleErrosNaoTratados(RuntimeException e) {
        return new ResponseError(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Ocorreu um erro em nosso servidor! Tente novamente mais tarde.",
            List.of()
        );
    }


}
