package com.honsu.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService service;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }

    @RequestMapping(value = "/todo-list", method = RequestMethod.GET)
    public String showTodoList(ModelMap model){
        model.addAttribute("todos", service.getTodos(GetLoggedInUserName()));
        return "todo-list";
    }

    private String GetLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showTodo(ModelMap model){
        model.addAttribute("todo", new Todo(0, "Honsu", "", new Date(), false));
        return "todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(@Valid Todo todo, BindingResult result, ModelMap model){
        if(result.hasErrors()){
            return "todo";
        }
        service.addTodo("Honsu", todo.getDesc(), new Date(), false);
        model.clear();
        return "redirect:todo-list";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String updateTodo(@RequestParam int id, ModelMap model){
        model.addAttribute("todo", service.retrieveTodo(id));
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(@Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        todo.setUser("Honsu");
        service.editTodo(todo);
        return "redirect:todo-list";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id, ModelMap model){
        service.deleteTodo(id);
        model.clear();
        return "redirect:todo-list";
    }
}
