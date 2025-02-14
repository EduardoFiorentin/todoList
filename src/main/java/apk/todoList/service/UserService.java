package apk.todoList.service;

import apk.todoList.model.User;
import apk.todoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public Optional<User> getById(UUID id) {
        return repository.findById(id);
    }

    public void delete(User user) {
        repository.delete(user);
    }

}
