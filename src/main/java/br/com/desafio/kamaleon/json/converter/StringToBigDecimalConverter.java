package br.com.desafio.kamaleon.json.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;


public class StringToBigDecimalConverter extends StdConverter<String, BigDecimal> {

    @Override
    public BigDecimal convert(String value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        String decimalFormatPattern = "##,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(decimalFormatPattern, symbols);
        decimalFormat.setDecimalSeparatorAlwaysShown(true);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setGroupingSize(3);

        BigDecimal bigDecimal = null;
        try {
            bigDecimal = new BigDecimal(String.valueOf(decimalFormat.parse(value)));
            bigDecimal.setScale(2);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return bigDecimal;
    }

}
