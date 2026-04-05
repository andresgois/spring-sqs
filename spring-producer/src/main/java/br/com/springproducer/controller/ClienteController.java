package br.com.springproducer.controller;

import br.com.springproducer.controller.apresentation.ClienteRecord;
import br.com.springproducer.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    private ResponseEntity<ClienteRecord> enviarSqs(@RequestBody ClienteRecord cliente) {
        service.enviarParaFila(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
