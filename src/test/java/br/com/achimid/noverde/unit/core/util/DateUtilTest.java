package br.com.achimid.noverde.unit.core.util;

import br.com.achimid.noverde.core.policy.PolicyFactory;
import br.com.achimid.noverde.core.util.DateUtils;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Calendar;

public class DateUtilTest {

    @Test
    public void testeDateToDateLocal() {
        Calendar c = Calendar.getInstance();

        LocalDateTime d = DateUtils.getInstance().convertToLocalDateTime(c.getTime());

        Assertions.assertEquals(d.getMinute(), c.get(Calendar.MINUTE));
    }

}
