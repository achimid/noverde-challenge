package br.com.achimid.noverde.unit.core.util;

import br.com.achimid.noverde.core.util.DateUtils;
import br.com.achimid.noverde.core.util.MathUtils;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;

public class MathUtilTest {

    MathUtils mathUtils = new MathUtils();

    @Test
    public void testeCalculaFormulaParcelaSimples() {

        val valorEmprestado = new BigDecimal("2000");
        val juros = new BigDecimal("5.5").divide(BigDecimal.valueOf(100));
        val parcelas = 6;

        BigDecimal valor = mathUtils.calculaFormulaParcela(valorEmprestado, juros, parcelas);

        Assertions.assertEquals(valor, BigDecimal.valueOf(400).setScale(2));

    }

    @Test
    public void testeCalculaFormulaParcelaValorZerado() {

        val valorEmprestado = BigDecimal.ZERO;
        val juros = new BigDecimal("5.5").divide(BigDecimal.valueOf(100));
        val parcelas = 6;

        BigDecimal valor = mathUtils.calculaFormulaParcela(valorEmprestado, juros, parcelas);

        Assertions.assertEquals(valor, BigDecimal.valueOf(0).setScale(2));

    }

    @Test
    public void testeCalculaFormulaParcelaJurosZerado() {

        val valorEmprestado = new BigDecimal("2000");
        val juros = new BigDecimal("0").divide(BigDecimal.valueOf(100));
        val parcelas = 6;

        Assertions.assertThrows(ArithmeticException.class, () -> {

            BigDecimal valor = mathUtils.calculaFormulaParcela(valorEmprestado, juros, parcelas);
        });
    }

    // TODO: implementar teste do metodo que calcula taxa de juros


}
