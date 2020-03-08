package br.com.desafio.kamaleon.json.converter.bigDecimal;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class BigDecimalToStringConverter extends StdConverter<BigDecimal, String> {

    @Override
    public String convert(BigDecimal bigDecimal) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        String pattern = "##,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setDecimalSeparatorAlwaysShown(true);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setGroupingSize(3);

        bigDecimal.setScale(2);
        return decimalFormat.format(bigDecimal);
    }

}
