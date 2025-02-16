package apk.todoList.controller.dto.commom;


import java.util.List;

public record ResponseError(int status, String message, List<ErrorField> errors
) {
}
