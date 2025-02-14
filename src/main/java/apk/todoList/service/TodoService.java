package apk.todoList.service;

import apk.todoList.model.Todo;
import apk.todoList.model.User;
import apk.todoList.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {

    @Autowired
    TodoRepository repository;

    public Todo save(Todo todo) {
        return repository.save(todo);
    }

    public Optional<Todo> getById(UUID id) {
        return repository.findById(id);
    }

    public void delete(Todo todo) {
        repository.delete(todo);
    }

    public List<Todo> getByUser(User user) {
        return repository.findByUser(user);
    }
}
