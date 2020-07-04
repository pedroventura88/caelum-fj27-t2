package br.com.alura.forum.service;

import br.com.alura.forum.mail.NewReplyMailTemplate;
import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.topic.domain.Topic;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor
@Service
public class ForumMailService {

    private JavaMailSender javaMailSender;
    private NewReplyMailTemplate newReplyMailTemplate;

    @Async
    public void sendNewReplyMail(Answer answer) {
        try {
            Topic answeredTopic = answer.getTopic();
            javaMailSender.send((mimeMessage) -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setTo(answeredTopic.getOwnerEmail());
                messageHelper.setSubject("Novo comentário em: " + answeredTopic.getShortDescription());
                messageHelper.setText(newReplyMailTemplate.getTemplate(answer), true);
            });
        } catch (MailException e) {
            log.error("Erro ao enviar e-mail para" + answer.getTopic().getOwnerEmail(), e.getMessage());
        }
    }

}