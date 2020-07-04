package br.com.alura.forum.task;

import br.com.alura.forum.model.OpenTopicsByCategory;
import br.com.alura.forum.repository.OpenTopicsByCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class RegisterUnansweredTopicsTask {

    private OpenTopicsByCategoryRepository openTopicsByCategoryRepository;

    @Scheduled(cron	= "0 0 0/1 1/1 * ?")
    public void execute() {
        openTopicsByCategoryRepository.deleteAll();
        List<OpenTopicsByCategory> openTopicsByCategories = openTopicsByCategoryRepository.findOpenTopicsByCategory();
        openTopicsByCategoryRepository.saveAll(openTopicsByCategories);
    }

}
