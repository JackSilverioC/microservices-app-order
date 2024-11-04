package com.dev.notification_service.service;

import com.dev.notification_service.kafka.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "order-placed", groupId = "notificationService")
    public void listen(OrderCreatedEvent orderCreatedEvent){
        log.info("Mensaje recibido: {}", orderCreatedEvent);
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springshop@email.com");
            messageHelper.setTo(orderCreatedEvent.getEmail());
            messageHelper.setSubject(String.format("Su pedido con número de orden %s ha sido registrado con éxito", orderCreatedEvent.getOrderNumber()));
            messageHelper.setText(String.format("""
                            Hola,

                            Su pedido con número de orden %s ha sido registrado exitosamente.
                            
                            Saludos cordiales,
                            Spring Shop
                            """,
                    orderCreatedEvent.getOrderNumber()));
        };
        try {
            javaMailSender.send(messagePreparator);
            log.info("Correo de confirmación de pedido enviado.");
        } catch (MailException e) {
            log.error("Ocurrió una excepción al enviar el correo", e);
            throw new RuntimeException("Ocurrió una excepción al enviar el correo a springshop@email.com", e);
        }
    }
}
