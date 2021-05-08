package com.ns.apps.vaccinestatus.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtility {

    public static String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static String getTomorrowsDate() {
        return LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

}
