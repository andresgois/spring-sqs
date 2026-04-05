package br.com.springconsumer.consumerHandle;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.springconsumer.Controller.apresentation.ClienteRecord;
import io.awspring.cloud.sqs.annotation.SqsListener;

@Component
public class Consumer {
    
    @SqsListener("minha-fila")
    public void listener(String payload) throws Exception {
        System.out.println("Payload >>> "+payload);
        ObjectMapper mapper = new ObjectMapper();

        ClienteRecord cliente = mapper.readValue(payload, ClienteRecord.class);
        System.out.println("Mensagem recebida: " + cliente.nome() + " - " + cliente.email());

    }
}
