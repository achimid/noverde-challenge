package br.com.achimid.noverde.loan.types;

public enum RefusedPolicyEnum {
    AGE("age"),
    SCORE("score"),
    COMMITMENT("commitment");

    private String valor;

    RefusedPolicyEnum(String valor){
        this.valor = valor;
    }
    public String getValor(){
        return this.valor;
    }
}
