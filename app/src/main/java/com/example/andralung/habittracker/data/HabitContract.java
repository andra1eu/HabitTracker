package com.example.andralung.habittracker.data;

import android.provider.BaseColumns;

public final class HabitContract {

    private HabitContract() {
    }

    public final static class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "javaLearning";

        public static final String WEEK_DAY_COLUMN = "weekDay";
        public static final String DAY_PART_COLUMN = "dayPart";
        public static final String LESSONS_NUMBER_COLUMN = "lessonsNumber";
    }
}
