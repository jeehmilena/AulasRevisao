package com.jessica.digitalhouse.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {

    @TypeConverter
    public List<Long> fromLong(String value) {
        Type listType = (Type) new TypeToken<List<Long>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromLong(List<Long> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

}
