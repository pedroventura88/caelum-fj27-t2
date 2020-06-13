package br.com.alura.forum.controller;

import br.com.alura.forum.annotation.ApiPageable;
import br.com.alura.forum.dto.input.TopicSearchDto;
import br.com.alura.forum.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@AllArgsConstructor
@RestController
public class TopicController {

    private TopicService topicService;

    @GetMapping(value="/api/topics")
    @ApiPageable
    public Page<TopicBriefOutputDto> getTopics(TopicSearchDto topicSearchDto, @ApiIgnore Pageable pageable) {
        return topicService.getTopics(topicSearchDto, pageable);
    }

}