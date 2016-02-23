package com.honsu.todo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todoList = new ArrayList<>();
    private static int todoCount = 3;


    static {
        todoList.add(new Todo(1, "Honsu", "Finish Spring MVC project", new Date(), false));
        todoList.add(new Todo(2, "Honsu", "Read 50 pages", new Date(), false));
        todoList.add(new Todo(3, "Honsu", "Evening meditation", new Date(), false));
    }

    public void addTodo(String name, String desc, Date targetDate, boolean isDone){
        todoList.add(new Todo(++todoCount, name, desc, targetDate, isDone));
    }

    public void deleteTodo(int id){
        Iterator<Todo> iterator = todoList.iterator();
        while(iterator.hasNext()){
            Todo todo = iterator.next();
            if(todo.getId() == id){
                iterator.remove();
            }
        }
    }

    public List<Todo> getTodos(String user){
        List<Todo> filteredTodos = new ArrayList<>();
        for(Todo todo : todoList){
            if(todo.getUser().equals(user)){
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    public Todo retrieveTodo(int id){
        for(Todo todo: todoList){
            if(todo.getId()==id){
                return todo;
            }
        }
        return null;
    }

    public void editTodo(Todo todo){
        todoList.remove(todo);
        todoList.add(todo);
    }
}
