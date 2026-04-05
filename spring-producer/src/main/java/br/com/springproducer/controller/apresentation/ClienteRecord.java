package br.com.springproducer.controller.apresentation;

import java.io.Serializable;

public record ClienteRecord(
        String nome,
        String email
) implements Serializable {

}
