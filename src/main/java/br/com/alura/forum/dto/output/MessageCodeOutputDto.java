package br.com.alura.forum.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageCodeOutputDto {

    private String message;
    private int httpCode;

    public static MessageCodeOutputDto of(String message, int httpCode) {
        return new MessageCodeOutputDto(message, httpCode);
    }

}
