package com.yc.electricity.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
/**
 * Created by Chuan on 3/20/16.
 */
public class DateConverter extends StrutsTypeConverter{
    /** Default date pattern is yyyy-MM-dd */
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd";
    private static final String DATETIME_PATTERN = "MM/dd/yyyy hh:mm a";

    /** {@inheritDoc} */
    @SuppressWarnings("rawtypes")
    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
        if (values != null && values.length > 0) {
            String value = values[0];
            if (value == null || value.trim().equals("")) {
                return null;
            }
            if (toClass == Date.class) {
                try {
                    value = value.toUpperCase();
                    if (value.length() > 17) {
                        return getFormatter(context, DATETIME_PATTERN).parse(value);
                    } else {
                        return getFormatter(context, DEFAULT_PATTERN).parse(value);
                    }
                } catch (ParseException e) {
                    throw new TypeConversionException(e);
                }
            }
        }
        return null;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("rawtypes")
    @Override
    public String convertToString(Map context, Object obj) {
        if (obj instanceof Date) {
            return getFormatter(context, DEFAULT_PATTERN).format((Date) obj);
        }
        return "";
    }

    @SuppressWarnings("rawtypes")
    private SimpleDateFormat getFormatter(Map context, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);
        return sdf;
    }
}
