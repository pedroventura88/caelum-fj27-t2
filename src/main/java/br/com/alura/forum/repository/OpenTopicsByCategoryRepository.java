package br.com.alura.forum.repository;

import br.com.alura.forum.model.OpenTopicsByCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpenTopicsByCategoryRepository extends JpaRepository<OpenTopicsByCategory, Long> {

    @Query("select new br.com.alura.forum.model.OpenTopicsByCategory(" +
            "t.course.subcategory.category.name as categoryName, " +
            "count(t) as topicCount, " +
            "now() as instant) from Topic t " +
            "where t.status = 'NOT_ANSWERED' " +
            "group by t.course.subcategory.category")
    List<OpenTopicsByCategory> findOpenTopicsByCategory();

    @Query("select t from OpenTopicsByCategory t " +
            "where year(t.date) = year(current_date) " +
            "and month(t.date) = month(current_date)")
    List<OpenTopicsByCategory> findAllByCurrentMonth();

}
