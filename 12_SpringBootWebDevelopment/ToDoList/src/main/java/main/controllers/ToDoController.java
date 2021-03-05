package main.controllers;

import main.entities.ToDo;
import main.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {

    private ToDoRepository toDoRepository;

    @Autowired
    public void setToDoRepository(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @PostMapping("/todolist/")
    public ResponseEntity add(
        @RequestBody ToDo toDo
    )   {
        ToDo newToDo = toDoRepository.save(toDo);
        if (newToDo.equals(toDo))   {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/todolist/{id}")
    public ResponseEntity update(
        @RequestBody ToDo toDo
    )   {
        toDoRepository.save(toDo);
        if (toDoRepository.findById(toDo.getId()).isPresent())    {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/todolist/{id}")
    public ResponseEntity get(@PathVariable Integer id) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (optionalToDo.isPresent())   {
            return new ResponseEntity(optionalToDo.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/todolist/{id}")
    public ResponseEntity remove(@PathVariable Integer id)   {
        toDoRepository.deleteById(id);
        if (!toDoRepository.findById(id).isPresent())   {
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/todolist/")
    public ResponseEntity toDoList()    {
        Iterable<ToDo> toDoIterable = toDoRepository.findAll();
        List<ToDo> toDoList = new ArrayList<>();
        for (ToDo toDo : toDoIterable)  {
            toDoList.add(toDo);
        }
        if (toDoList != null)   {
            return new ResponseEntity(toDoList, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}