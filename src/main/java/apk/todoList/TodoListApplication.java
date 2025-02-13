package apk.todoList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TodoListApplication {

	@GetMapping("/hello")
	public String hello() {
		return "Rélou wordi!";
	}

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

}
