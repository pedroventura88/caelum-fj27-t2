package br.com.alura.forum.service;

import br.com.alura.forum.dto.input.CreateTopicDto;
import br.com.alura.forum.dto.output.TopicOutputDto;
import br.com.alura.forum.exception.ResourceNotFoundException;
import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TopicServiceTest {

    private TopicService topicService;

    @Mock
    private TopicRepository topicRepository;

    @Mock
    private CourseRepository courseRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        topicService = new TopicService(topicRepository, courseRepository);
    }

    @Test
    public void createTopicSuccess() {
        Course curso = new Course("Java", new Category("Programação", new Category("TI")));

        Topic topic = new Topic("Como fazer teste unitário", null, new User(), curso);
        topic.setId(1L);

        when(courseRepository.findByName(anyString())).thenReturn(Optional.ofNullable(curso));
        when(topicRepository.save(any())).thenReturn(topic);

        CreateTopicDto createTopicDto = new CreateTopicDto("Como fazer teste unitário", null, "Java");
        TopicOutputDto topicOutputDto = topicService.createTopic(createTopicDto, new User());

        assertEquals("Como fazer teste unitário", topicOutputDto.getShortDescription());
        assertEquals("Java", topicOutputDto.getCourseName());

        verify(courseRepository, times(1)).findByName(any());
        verify(topicRepository, times(1)).save(any());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void createTopicShouldThrowResourceNotFoundExceptionWhenCourseNameNotExists() {
        when(courseRepository.findByName(anyString())).thenThrow(ResourceNotFoundException.class);

        CreateTopicDto createTopicDto = new CreateTopicDto("Como fazer teste unitário", null, "InvalidCourseName");
        topicService.createTopic(createTopicDto, new User());

        verify(courseRepository, times(1)).findByName(any());
        verify(topicRepository, times(0)).save(any());
    }

}
