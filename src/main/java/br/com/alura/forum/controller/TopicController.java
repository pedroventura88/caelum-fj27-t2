package br.com.alura.forum.controller;

import br.com.alura.forum.annotation.ApiPageable;
import br.com.alura.forum.dto.input.TopicSearchDto;
import br.com.alura.forum.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.repository.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static br.com.alura.forum.dto.output.TopicBriefOutputDto.listFromTopics;

@RestController
public class TopicController {

    private TopicRepository topicRepository;

    public TopicController(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @GetMapping(value="/api/search")
    @ApiPageable
    public Page<TopicBriefOutputDto> listarPorStatusCategoria(TopicSearchDto topicSearchDto, @ApiIgnore Pageable pageable) {
        Page<Topic> topicos = topicRepository.findAll(topicSearchDto.toSpecification(), pageable);
        return listFromTopics(topicos);
    }

    @GetMapping(value="/api/topics", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<TopicBriefOutputDto> topicList() {
        return listFromTopics(topicRepository.findAll());
    }

}