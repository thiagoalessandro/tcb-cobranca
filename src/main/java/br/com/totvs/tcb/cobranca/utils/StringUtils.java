package br.com.totvs.tcb.cobranca.utils;

import java.text.Normalizer;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String noNull(String value) {
        return value.replace("null", "");
    }

    public static String trataNull(String value) {
        return value != null && !value.equals("null") ? value : null;
    }

    public static Long gerarCodigoMovimento() {
        String cdPrefix = String.valueOf(new Date().getTime());
        String cdSufix = StringUtils.leftPad(String.valueOf(new Random().nextInt(99)), 2, "0");
        Long cdHash = Long.parseLong(cdPrefix.concat(cdSufix));
        return cdHash;
    }

    public static String formataCodigoBanco(Integer codigoBanco) {
        return StringUtils.leftPad(String.valueOf(codigoBanco), 3, "0");
    }

    public static String limpaCaracterEspecial(String value) {
        String nfdNormalizedString = Normalizer.normalize(value, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("")
                .replaceAll("\\.", "")
                .replaceAll("/", "")
                .replaceAll("_", "")
                .replaceAll("\\\\", "")
                .trim()
                .replaceAll(" ", "-")
                .toLowerCase();
    }

}
