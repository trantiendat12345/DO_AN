package com.example.EduCenter_BE.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class BaseService {

    public static LocalDate calculateEndDate(
            LocalDate startDate,
            int totalSessions,
            Set<DayOfWeek> studyDays
    ) {
        LocalDate date = startDate;
        int count = 0;

        while (count < totalSessions) {
            if (studyDays.contains(date.getDayOfWeek())) {
                count++;
            }
            if (count < totalSessions) {
                date = date.plusDays(1);
            }
        }
        return date;
    }

}


