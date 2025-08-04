package br.com.fiap.epictask.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public String index(Model model){
        System.out.println("Mostrando a página de tarefas");
        var tasks = List.of("tarefa1", "tarefa2", "tarefa3");

        model.addAttribute("tasks", taskService.getAllTasks());
        return "index";

    }
}
