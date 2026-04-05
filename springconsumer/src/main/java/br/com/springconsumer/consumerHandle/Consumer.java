package br.com.springconsumer.consumerHandle;

import org.springframework.stereotype.Component;

import br.com.springconsumer.Controller.apresentation.ClienteRecord;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import io.awspring.cloud.sqs.annotation.SqsListener;

@Component
public class Consumer {
    
    @SqsListener("minha-fila")
    public void listener(ClienteRecord message) {
        System.out.println("Mensagem recebida: " + message.nome() + " - " + message.email());
    }
}
