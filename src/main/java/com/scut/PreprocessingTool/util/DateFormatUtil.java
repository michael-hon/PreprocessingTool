package com.scut.PreprocessingTool.util;

import java.text.*;
import java.util.*;

public class DateFormatUtil {
    private static final String pattern="yyyy-MM-dd HH:mm:ss";

    private static SimpleDateFormat format = new SimpleDateFormat(pattern);

    public static String formatDate(Long time){

        return format.format(new Date(time));
    }
}
