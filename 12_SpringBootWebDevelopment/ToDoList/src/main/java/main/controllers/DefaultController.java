package main.controllers;

import main.entities.ToDo;
import main.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController {

    private ToDoRepository toDoRepository;

    @Autowired
    public void setToDoRepository(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @RequestMapping("/")
    public String index(Model model)  {

        Iterable<ToDo> toDoIterable = toDoRepository.findAll();
        List<ToDo> toDoList = new ArrayList<>();
        for (ToDo toDo : toDoIterable)  {
            toDoList.add(toDo);
        }
        model.addAttribute("todolist", toDoList);
        return "index";

    }

}