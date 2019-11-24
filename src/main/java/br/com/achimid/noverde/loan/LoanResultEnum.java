package br.com.achimid.noverde.loan;

public enum LoanResultEnum {
    APPROVED("approved"),
    REFUSED("refused");

    private String valor;

    LoanResultEnum(String valor){
        this.valor = valor;
    }
    public String getValor(){
        return this.valor;
    }
}
