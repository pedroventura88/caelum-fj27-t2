package br.com.alura.forum.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicStatistic {

    private Long id;
    private String nome;
    private Long quantidade;

}
