package apk.todoList.controller.dto.user;

import apk.todoList.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UserDTO (
    UUID id,
    @NotBlank(message = "Campo é obrigatório.")
    @Size(min = 1, max = 255, message = "Campo deve ter tamanho entre 1 e 255 caracteres.")
    String name,

    @NotBlank(message = "Campo é obrigatório.")
    @Email(message = "Formato invalido")
    String email,

    @NotBlank(message = "Campo é obrigatório.")
    @Size(min = 1, max = 255, message = "Campo deve ter tamanho entre 1 e 255 caracteres.")
    String password
) {
    public User mapToUser() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);

        return user;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
