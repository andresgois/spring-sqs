package br.com.springproducer.service;

import br.com.springproducer.controller.apresentation.ClienteRecord;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ClienteService {

    @Autowired
    private SqsTemplate sqsTemplate;

    public void enviarParaFila(ClienteRecord cliente) {
        String sqs = "http://localhost:4566/000000000000/minha-fila";
        sqsTemplate.send(builder -> builder
                .queue(sqs)
                .payload(cliente)
                .header("JavaType", null) // 🔥 REMOVE O PROBLEMA
        );
        log.info("Enviado pra fila >>>> "+cliente.email());
    }
}
