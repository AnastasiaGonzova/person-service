package core.listener;

import core.configuration.RabbitMQConfiguration;
import core.service.RabbitReceiveServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MessageListener {

    private final RabbitReceiveServiceImpl rabbitReceiveService;

    @RabbitListener(queues = RabbitMQConfiguration.DAILY_QUEUE_NAME)
    public void receiveDailyMessage(String message) {
        rabbitReceiveService.receiveMessage(message, RabbitMQConfiguration.DAILY_QUEUE_NAME);
    }


    @RabbitListener(queues = RabbitMQConfiguration.ALERT_QUEUE_NAME)
    public void receiveAlertMessage(String message) {
        rabbitReceiveService.receiveMessage(message, RabbitMQConfiguration.ALERT_QUEUE_NAME);
    }

    @RabbitListener(queues = RabbitMQConfiguration.ERROR_QUEUE_NAME)
    public void receiveErrorMessage(String message) {
        rabbitReceiveService.receiveMessage(message, RabbitMQConfiguration.ERROR_QUEUE_NAME);
    }
}
