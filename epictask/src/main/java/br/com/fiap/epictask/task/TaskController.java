package br.com.fiap.epictask.task;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final MessageSource messageSource;

    public TaskController(TaskService taskService, MessageSource messageSource){
        this.taskService = taskService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user){
        var avatar = user.getAttributes().get("picture") != null?
                user.getAttributes().get("picture") :
                user.getAttributes().get("avatar_url");
        System.out.println("Mostrando a página de tarefas");
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("user", user);
        return "index";

    }

    @GetMapping("/form")
    public String form(Task task){
        return "form";
    }

    @PostMapping("/form")
    public String creat(@Valid Task task, BindingResult result, RedirectAttributes redirect){ //TO DO:  DTO
        if (result.hasErrors()){
            return "form";
        }
        taskService.save(task);
        System.out.println(task);
        var message = messageSource.getMessage("task.save.success", null, LocaleContextHolder.getLocale());
        redirect.addFlashAttribute("message", message);
        return "redirect:/task";
    }
}
