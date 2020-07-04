package br.com.alura.forum.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTopicDto {

    @NotBlank
    @Size(min = 10, max = 123123)
    private	String shortDescription;

    private	String content;

    @NotBlank
    private	String courseName;

}