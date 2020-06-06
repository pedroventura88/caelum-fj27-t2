package br.com.alura.forum.controller;

import br.com.alura.forum.dto.input.TopicSearchDto;
import br.com.alura.forum.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.repository.TopicRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.alura.forum.dto.output.TopicBriefOutputDto.listFromTopics;

@RestController
public class TopicController {

    private TopicRepository topicRepository;

    public TopicController(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @GetMapping(value="/api/search")
    public List<TopicBriefOutputDto> listarPorStatusCategoria(TopicSearchDto topicSearchDto) {
        return listFromTopics(topicRepository.findAll(topicSearchDto.toSpecification()));
    }

    @GetMapping(value="/api/topics", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<TopicBriefOutputDto> topicList() {
        return listFromTopics(topicRepository.findAll());
    }

}