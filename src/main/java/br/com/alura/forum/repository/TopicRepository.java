package br.com.alura.forum.repository;

import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.topic.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("select	t	from	Topic	t	where	t.course	=	:course")
    List<Topic> findByCourseOld(@Param("course") Course course);

    List<Topic>	findByCourse(Course	course);

    List<Topic>	findByCourseNameOrderByCreationInstantDesc(String	courseName);

}
