package br.com.springconsumer.Controller.apresentation;

import java.io.Serializable;

public record ClienteRecord(
    String nome,
    String email
) implements Serializable{
    
}
