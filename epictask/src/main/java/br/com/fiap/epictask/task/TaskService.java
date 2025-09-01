package br.com.fiap.epictask.task;


import br.com.fiap.epictask.helper.MessageHelper;
import br.com.fiap.epictask.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final MessageSource messageSource;
    private final MessageHelper messageHelper;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task save(Task task) {
       return taskRepository.save(task);
    }

    public Task getTask(Long id){
        return getOrElseThrow(id);
    }

    private Task getOrElseThrow(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageHelper.getMessage("task.notfound")));
    }

    public void deleteById(Long id){

        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException(messageHelper.getMessage("task.notfound"));
        }
        taskRepository.deleteById(id);
    }

    public void pickTask(Long id, User user) {
        var task = getOrElseThrow(id);
        task.setUser(user);
        taskRepository.save(task);

    }
    public void dropTask(Long id, User user) {
        var task = getOrElseThrow(id);
        task.setUser(null);
        taskRepository.save(task);

    }



    public void incrementTaskStatus(Long id, User user) {
        var task = getOrElseThrow(id);
        task.setStatus(task.getStatus() + 10);
        if (task.getStatus() > 100) {
            task.setStatus(100);
        }
        taskRepository.save(task);

    }
    public void decrementTaskStatus(Long id, User user) {
        var task = getOrElseThrow(id);
        task.setStatus(task.setStatus() - 10);
        if (task.getStatus() < 0) {
            task.setStatus(0);
        }
        taskRepository.save(task);

    }
}
