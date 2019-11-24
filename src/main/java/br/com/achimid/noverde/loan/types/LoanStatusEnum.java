package br.com.achimid.noverde.loan.types;

public enum LoanStatusEnum {
    PROCESSING("processing"),
    COMPLETED("completed");

    private String valor;

    LoanStatusEnum(String valor){
        this.valor = valor;
    }
    public String getValor(){
        return this.valor;
    }
}
