package br.com.totvs.tcb.cobranca.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils extends org.apache.commons.lang3.StringUtils {

    public static String dateToString() {
        return String.valueOf(new Date().getTime());
    }

    public static String dateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

}
