package br.com.springconsumer.config.converter;

import io.awspring.cloud.sqs.support.converter.SqsMessagingMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SqsConverter {

    @Bean
    public SqsMessagingMessageConverter converter() {
        SqsMessagingMessageConverter converter = new SqsMessagingMessageConverter();
        converter.setPayloadTypeMapper(message -> String.class); // força String
        return converter;
    }

}
