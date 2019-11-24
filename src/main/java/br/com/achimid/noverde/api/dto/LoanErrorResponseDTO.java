package br.com.achimid.noverde.api.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class LoanErrorResponseDTO {

    // Acredito que essa não é a melhor maneira de retornar os erros, mas fiz assim pois foi solicitado no enunciado.
    // Talvez fosse melhor utilizar o tratamento de erro padrão.
    private ArrayList<String> errors;

    public LoanErrorResponseDTO() {
        this.errors = new ArrayList();
    }

    public LoanErrorResponseDTO(String err) {
        this();
        this.errors.add(err);
    }


}
