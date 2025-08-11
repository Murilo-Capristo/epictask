package br.com.fiap.epictask.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("tasks", taskService.getAllTasks());
        return "index";

    }

    @GetMapping("/form")
    public String form(){
        return "form";
    }

    @PostMapping("/form")
    public String creat(Task task, RedirectAttributes redirect){ //TO DO:  DTO
        taskService.save(task);
        System.out.println(task);
        redirect.addFlashAttribute("message", "Tarefa cadastrada com sucesso!");
        return "redirect:/task";
    }
}
