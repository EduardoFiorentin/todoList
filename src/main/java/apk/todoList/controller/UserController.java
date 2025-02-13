package apk.todoList.controller;

import apk.todoList.controller.dto.UserDTO;
import apk.todoList.model.User;
import apk.todoList.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping("{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") String id) {
        UUID userId = UUID.fromString(id);
        Optional<User> user = repository.findById(userId);

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.get().mapToDTO());
    }

    @PostMapping
    public ResponseEntity<Void> craete(@RequestBody UserDTO dto) {
        User newUser = dto.mapToUser();
        repository.save(newUser);

        URI headerLocationUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();

        return ResponseEntity.created(headerLocationUri).build();
    }
}
