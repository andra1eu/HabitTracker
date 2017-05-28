package com.example.andralung.habittracker.model;

import android.content.ContentValues;

import static com.example.andralung.habittracker.data.HabitContract.HabitEntry.DAY_PART_COLUMN;
import static com.example.andralung.habittracker.data.HabitContract.HabitEntry.LESSONS_NUMBER_COLUMN;
import static com.example.andralung.habittracker.data.HabitContract.HabitEntry.WEEK_DAY_COLUMN;

public class Habit {

    private final String weekDay;
    private final String dayPart;
    private final int lessonsNumber;

    public Habit(String weekDay, String dayPart, int lessonsNumber) {
        this.weekDay = weekDay;
        this.dayPart = dayPart;
        this.lessonsNumber = lessonsNumber;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public int getLessonsNumber() {
        return lessonsNumber;
    }

    public String getDayPart() {
        return dayPart;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put(WEEK_DAY_COLUMN, weekDay);
        values.put(DAY_PART_COLUMN, dayPart);
        values.put(LESSONS_NUMBER_COLUMN, lessonsNumber);

        return values;
    }
}
