package br.com.alura.forum.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class OpenTopicsByCategory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    private int topicCount;
    private LocalDate date;

    public OpenTopicsByCategory(String categoryName, Number topicCount, Date instant) {
        this.categoryName = categoryName;
        this.topicCount = topicCount.intValue();
        this.date = instant.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
