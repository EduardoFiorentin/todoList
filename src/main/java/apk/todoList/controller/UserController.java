package apk.todoList.controller;

import apk.todoList.controller.dto.user.UserDTO;
import apk.todoList.model.User;
import apk.todoList.repository.UserRepository;
import apk.todoList.service.UserService;
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

    @Autowired
    UserService service;


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
        service.save(newUser);

        URI headerLocationUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();

        return ResponseEntity.created(headerLocationUri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(
            @PathVariable("id") String id,
            @RequestBody UserDTO dto
    ) {

        var user = service.getById(UUID.fromString(id));

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User uptUser = user.get();
        if (dto.name() != null) {
            uptUser.setName(dto.name());
        }
        if (dto.password() != null) {
            uptUser.setPassword(dto.password());
        }
        if (dto.email() != null) {
            uptUser.setEmail(dto.email());
        }
        service.save(uptUser);

        return new ResponseEntity<Object>(uptUser.mapToDTO(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete (
            @PathVariable("id") String id
    ){

        Optional<User> user = service.getById(UUID.fromString(id));

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.delete(user.get());
        return ResponseEntity.noContent().build();
    }
}
