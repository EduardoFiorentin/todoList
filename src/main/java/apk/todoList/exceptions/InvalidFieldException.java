package apk.todoList.exceptions;

public class InvalidFieldException extends RuntimeException{
    public String field;

    public InvalidFieldException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField(){
        return this.field;
    }

}
