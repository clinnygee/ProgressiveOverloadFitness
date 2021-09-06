package com.example.progressiveoverloadfitness.database.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise")
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exercise_id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "body_part")
    private String bodyPart;

    public Exercise(@NonNull String name, String description, @NonNull String bodyPart){
        this.name = name;
        this.description = description;
        this.bodyPart = bodyPart;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public String getBodyPart(){
        return this.bodyPart;
    }

    public int getId(){ return this.id;}

    public void setId(int id){ this.id = id;}
}
