package com.honsu.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoRESTController {

    @Autowired
    TodoService service;

    @RequestMapping(path="/todos")
    public List<Todo> retrieveAllTodos(){
        return service.getTodos("Honsu");
    }

    @RequestMapping(path="/todos/{id}")
    public Todo retrieveTodo(@PathVariable int id){
        return service.retrieveTodo(id);
    }
}
