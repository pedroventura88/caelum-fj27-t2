package br.com.alura.forum.controller;

import br.com.alura.forum.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicController {

    @GetMapping(value="/api/topics", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<TopicBriefOutputDto> topicList() {
        Topic topic = new Topic(
                "Dúvida sobre swagger",
                "Como faço pra documentar um endpoint que está fora da pasta padrão",
                new User("Rafael", "rafaeltavares91@gmail.com", "hehe11"),
                new Course("FJ-27 Spring", new Category("Java Backend", new Category("Java Plataforma"))));

        return TopicBriefOutputDto.listFromTopics(List.of(topic));
    }

}
