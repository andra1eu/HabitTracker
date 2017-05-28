package com.example.andralung.habittracker.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.andralung.habittracker.model.Habit;

import static com.example.andralung.habittracker.data.HabitContract.HabitEntry._ID;
import static com.example.andralung.habittracker.data.HabitContract.HabitEntry.DAY_PART_COLUMN;
import static com.example.andralung.habittracker.data.HabitContract.HabitEntry.LESSONS_NUMBER_COLUMN;
import static com.example.andralung.habittracker.data.HabitContract.HabitEntry.TABLE_NAME;
import static com.example.andralung.habittracker.data.HabitContract.HabitEntry.WEEK_DAY_COLUMN;

public class HabitDatabase {

    private final SQLiteDatabase db;

    public HabitDatabase(Context context) {
        db = new DatabaseHelper(context).getWritableDatabase();
    }

    public void insert(Habit habit) {
        db.insert("", null, habit.getValues());
    }

    public Cursor getHabit(String dayPart) {
        String[] columns = new String[]{WEEK_DAY_COLUMN, DAY_PART_COLUMN, LESSONS_NUMBER_COLUMN};
        String selection = DAY_PART_COLUMN + "=?";
        String[] selectionArgs = new String[]{dayPart};
        
        return db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
    }

    public Habit getHabitByWeekDay(String weekDay) {
        Habit habit = null;
        String[] columns = new String[]{WEEK_DAY_COLUMN, DAY_PART_COLUMN, LESSONS_NUMBER_COLUMN};
        String selection = WEEK_DAY_COLUMN + "=?";
        String[] selectionArgs = new String[]{weekDay};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String week = cursor.getString(cursor.getColumnIndex(WEEK_DAY_COLUMN));
                String dayPart = cursor.getString(cursor.getColumnIndex(DAY_PART_COLUMN));
                int lessons = cursor.getInt(cursor.getColumnIndex(LESSONS_NUMBER_COLUMN));

                habit = new Habit(week, dayPart, lessons);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return habit;
    }

    private class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "habit.db";
        private static final int DATABASE_VERSION = 1;

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            String SQL_CREATE_HABIT_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    WEEK_DAY_COLUMN + " TEXT NOT NULL, " +
                    DAY_PART_COLUMN + " TEXT, " +
                    LESSONS_NUMBER_COLUMN + " INTEGER NOT NULL);";

            Log.d("create table", SQL_CREATE_HABIT_TABLE);
            db.execSQL(SQL_CREATE_HABIT_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
