package apk.todoList.controller.dto;

import apk.todoList.model.User;

import java.util.UUID;

public record UserDTO (
    UUID id,
    String name,
    String email,
    String password
) {
    public User mapToUser() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);

        return user;
    }


}
