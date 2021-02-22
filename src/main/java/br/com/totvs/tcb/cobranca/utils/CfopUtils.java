package br.com.totvs.tcb.cobranca.utils;

import java.math.BigDecimal;

public class CfopUtils {
    public static BigDecimal convertMicroAmount(Long acount) {
        return new BigDecimal(acount).divide(new BigDecimal(1000000L));
    }

}
