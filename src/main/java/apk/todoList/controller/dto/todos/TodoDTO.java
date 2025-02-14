package apk.todoList.controller.dto.todos;

import apk.todoList.model.Todo;
import apk.todoList.model.User;

public record TodoDTO(
        String title,
        String description,
        Boolean completed,
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
