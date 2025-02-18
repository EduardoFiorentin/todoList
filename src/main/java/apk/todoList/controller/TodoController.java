package apk.todoList.controller;

import apk.todoList.controller.dto.todos.TodoDTO;
import apk.todoList.exceptions.InvalidFieldException;
import apk.todoList.model.Todo;
import apk.todoList.model.User;
import apk.todoList.service.TodoService;
import apk.todoList.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("todos")
public class TodoController {

    @Autowired
    private TodoService service;

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<Todo> getById(
            @PathVariable("id") String id
    ) {
        var todo = service.getById(UUID.fromString(id));

        if (todo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(todo.get());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Todo>> getByUser(
            @PathVariable String id
    ) {

        Optional<User> user = userService.getById(UUID.fromString(id));
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var todos = service.getByUser(user.get());

        return ResponseEntity.ok(todos);

    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid TodoDTO dto) {


        Optional<User> user = userService.getById(UUID.fromString(dto.user()));

        if (user.isEmpty()) {
            throw new InvalidFieldException("user", "User não encontrado!");
        }

        Todo todo = dto.mapToEntity(user.get());
        service.save(todo);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(todo.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(
            @PathVariable String id,
            @RequestBody @Valid TodoDTO dto
    ) {
        Optional<Todo> todoOpt = service.getById(UUID.fromString(id));
        if (todoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Todo todo = todoOpt.get();
        if (dto.title() != null) {
            todo.setTitle(dto.title());
        }
        if (dto.description() != null) {
            todo.setDescription(dto.description());
        }
        if (dto.completed() != null) {
            todo.setCompleted(dto.completed());
        }
        service.save(todo);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id
    ) {

        Optional<Todo> todo = service.getById(UUID.fromString(id));

        if (todo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.delete(todo.get());
        return ResponseEntity.noContent().build();
    }
}

