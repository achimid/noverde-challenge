package br.com.achimid.noverde.core.util;

import br.com.achimid.noverde.loan.Loan;
import br.com.achimid.noverde.loan.types.LoanTermsEnum;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

@Component
public class MathUtils {

    public BigDecimal calcularValorParcela(@NotNull Loan loan) {
        return this.calcularValorParcela(loan, loan.getTerms().getValorI());
    }

    private BigDecimal calcularValorParcela(@NotNull Loan loan, Integer parcela) {

        Integer score = loan.getScore();
        Integer parcelasSolicitadas = loan.getTerms().getValorI();
        BigDecimal vEmprestimo = loan.getAmount();

        BigDecimal juros = this.calculaTaxaDeJuros(score, parcelasSolicitadas);
        BigDecimal vParcelaCalculada = this.calculaFormulaParcela(vEmprestimo, juros, parcelasSolicitadas);

        // Incremento de parcelas caso a parcela seja mais cara do que pode ser pago
        if (vParcelaCalculada.compareTo(loan.getValorSalarioNaoComprometido()) > 0) {
            val parcelasValidas = Arrays.asList(LoanTermsEnum.values());
            val index = parcelasValidas.indexOf(LoanTermsEnum.valueOf("X" + parcela));

            // Condição de parada
            if (index >= parcelasValidas.size() - 1) return vParcelaCalculada;

            // Recursão
            return this.calcularValorParcela(loan, parcelasValidas.get(index + 1).getValorI());
        }

        if (loan.isApproved()) {
            val process = loan.getProcess();
            process.setAmount(vParcelaCalculada);
            process.setTerms(parcela);
        }

        return vParcelaCalculada;
    }

    /**
     * formula
     * PMT = PV / (1 + i)^n - 1 / (1 + i)^n * i
     */
    public BigDecimal calculaFormulaParcela(BigDecimal pv, BigDecimal juros, Integer parcelas) {

        //(1 + i)^n - 1
        BigDecimal t1 = BigDecimal.ONE.add(juros).pow(parcelas).subtract(BigDecimal.ONE);

        //(1 + i)^n * i
        BigDecimal t2 = BigDecimal.ONE.add(juros).pow(parcelas).multiply(juros);

        //(1 + i)^n - 1 / (1 + i)^n * i -> t1 / t2
        BigDecimal taxa = t1.divide(t2, 2, RoundingMode.HALF_UP);

        BigDecimal total = pv.divide(taxa, 2, RoundingMode.HALF_UP);

        return total;
    }

    /**
     * score 	6 parcelas 	9 parcelas 	12 parcelas
     * 900 ou mais 	3,9% 	4,2% 	4,5%
     * 800 a 899 	4,7% 	5,0% 	5,3%
     * 700 a 799 	5,5% 	5,8% 	6,1%
     * 600 a 699 	6,4% 	6,6% 	6,9%
     * */
    public BigDecimal calculaTaxaDeJuros(Integer score, Integer parcelas) {
        switch (parcelas) {
            case 6:
                if (score >= 900) {
                    return new BigDecimal("3.9").divide(BigDecimal.valueOf(100));
                } else if (score >= 800) {
                    return new BigDecimal("4.7").divide(BigDecimal.valueOf(100));
                } else if (score >= 700) {
                    return new BigDecimal("5.5").divide(BigDecimal.valueOf(100));
                } else if (score >= 600) {
                    return new BigDecimal("6.4").divide(BigDecimal.valueOf(100));
                }
                break;
            case 9:
                if (score >= 900) {
                    return new BigDecimal("4.2").divide(BigDecimal.valueOf(100));
                } else if (score >= 800) {
                    return new BigDecimal("5.0").divide(BigDecimal.valueOf(100));
                } else if (score >= 700) {
                    return new BigDecimal("5.8").divide(BigDecimal.valueOf(100));
                } else if (score >= 600) {
                    return new BigDecimal("6.6").divide(BigDecimal.valueOf(100));
                }
                break;
            case 12:
                if (score >= 900) {
                    return new BigDecimal("4.5").divide(BigDecimal.valueOf(100));
                } else if (score >= 800) {
                    return new BigDecimal("5.3").divide(BigDecimal.valueOf(100));
                } else if (score >= 700) {
                    return new BigDecimal("6.1").divide(BigDecimal.valueOf(100));
                } else if (score >= 600) {
                    return new BigDecimal("6.9").divide(BigDecimal.valueOf(100));
                }
                break;
        }

        return BigDecimal.ZERO;
    }


}
