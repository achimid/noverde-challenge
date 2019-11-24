package br.com.achimid.noverde.loan.types;

import java.math.BigDecimal;

public enum LoanTermsEnum {
    X6("6"),
    X9("9"),
    X12("12");

    private String valor;

    LoanTermsEnum(String valor){
        this.valor = valor;
    }

    public String getValor(){
        return this.valor;
    }
    public BigDecimal getValorBg(){
        return new BigDecimal(this.valor);
    }
}
