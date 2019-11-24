package br.com.achimid.noverde.core.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {
    private static DateUtils ourInstance = new DateUtils();

    public static DateUtils getInstance() {
        return ourInstance;
    }

    private DateUtils() {
    }

    public LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
