package apk.todoList.controller.dto;

import apk.todoList.model.User;

import java.util.UUID;

public record UserResponseDTO (
        String name,
        String email
) {}