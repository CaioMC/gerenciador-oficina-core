package com.fiap.pj.core.email.app;

import com.fiap.pj.core.email.app.gateways.EmailGateway;
import com.fiap.pj.core.email.app.usecase.EnviarEmailUseCase;
import com.fiap.pj.core.email.app.usecase.command.EnviarEmailCommand;
import com.fiap.pj.core.email.domain.EmailTemplate;
import com.fiap.pj.core.email.domain.enums.Template;
import com.fiap.pj.core.email.exception.EmailTemplateExceptions.EmailTemplateNaoEncontradoException;
import com.fiap.pj.core.email.exception.EmailTemplateExceptions.EmailTemplateNaoFoiPossivelEnviarEmailException;
import com.fiap.pj.core.util.EmailTemplateUtils;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.CollectionUtils;

import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
public class EnviarEmailUseCaseImpl implements EnviarEmailUseCase {

    public static final String UTF_8_ENCONDING = "UTF-8";
    public static final String EXCEPTION_FALHA_ENVIO_EMAIL_MESSAGE = "Não foi possível enviar o email ao destinatário.";

    private final EmailGateway emailGateway;
    private final JavaMailSender mailSender;

    public EnviarEmailUseCaseImpl(EmailGateway emailGateway, JavaMailSender mailSender) {
        this.emailGateway = emailGateway;
        this.mailSender = mailSender;
    }

    @Override
    public void handle(EnviarEmailCommand cmd) {
        try {
            EmailTemplate emailTemplate = this.emailGateway.buscarTemplate(cmd.template())
                    .orElseGet(() -> this.buscarTemplateDoResource(cmd.template()));

            MimeMessage message = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCONDING);

            helper.setTo(cmd.destinatario());
            helper.setSubject(emailTemplate.getAssunto());
            helper.setText(
                    this.getFormattedText(cmd, emailTemplate),
                    true
            );
            this.mailSender.send(message);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new EmailTemplateNaoFoiPossivelEnviarEmailException(
                    EXCEPTION_FALHA_ENVIO_EMAIL_MESSAGE,
                    e
            );
        }
    }

    private EmailTemplate buscarTemplateDoResource(Template templateEnum) {
        try {
            String nomeArquivo = templateEnum.name().toLowerCase() + ".html";

            String corpo = EmailTemplateUtils.readEmailTemplateAsString(nomeArquivo);
            String assunto = templateEnum.getAssuntoPadrao();

            return new EmailTemplate(
                    UUID.randomUUID(),
                    templateEnum,
                    assunto,
                    corpo,
                    ZonedDateTime.now()
            );
        } catch (Exception e) {
            throw new EmailTemplateNaoEncontradoException();
        }
    }

    private String getFormattedText(EnviarEmailCommand cmd, EmailTemplate emailTemplate) {
        return CollectionUtils.isEmpty(cmd.args())
                ? emailTemplate.getCorpo()
                : String.format(emailTemplate.getCorpo(), cmd.args().toArray());
    }
}