package apk.todoList.controller.dto.todos;

import apk.todoList.model.Todo;
import apk.todoList.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TodoDTO(
        @NotBlank(message = "Campo é obrigatório.")
        @Size(min = 1, max = 255, message = "Campo deve ter tamanho entre 1 e 255 caracteres.")
        String title,

        @NotBlank(message = "Campo é obrigatório.")
        @Size(min = 1, max = 255, message = "Campo deve ter tamanho entre 1 e 255 caracteres.")
        String description,

        @NotNull(message = "Campo é obrigatório.")
        Boolean completed,

        @Pattern(regexp="^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "Formato inválido.")
        String user
) {
    public Todo mapToEntity(User user) {
        Todo todo = new Todo();

        todo.setTitle(title);
        todo.setDescription(description);
        todo.setCompleted(completed);
        todo.setUser(user);

        return todo;
    }

    @Override
    public String toString() {
        return "TodoDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}
