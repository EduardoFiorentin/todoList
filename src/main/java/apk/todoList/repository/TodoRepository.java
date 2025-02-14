package apk.todoList.repository;

import apk.todoList.model.Todo;
import apk.todoList.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TodoRepository extends JpaRepository<Todo, UUID> {
    List<Todo> findByUser(User user);
}
