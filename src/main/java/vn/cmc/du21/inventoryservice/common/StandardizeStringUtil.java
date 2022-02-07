package vn.cmc.du21.inventoryservice.common;

import java.util.Locale;

public class StandardizeStringUtil {
    public static String standardizeString(String s){
        return s.trim().replaceAll("\\s+", " ").toLowerCase(Locale.ROOT);
    }
}