package com.ubtech.jimuplus.database.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AudioEntity.TABLE_AUDIO_INFO)
public class AudioEntity {
    public static final String TABLE_AUDIO_INFO = "audio_info";

    @PrimaryKey
    private long id;
    private String name;
    private long duration;
    private String path;
    private boolean isSystem;
    private String absolutePath;

    public AudioEntity() {
    }

    public AudioEntity(long id, String name, long duration, String path, boolean isSystem, String absolutePath) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.path = path;
        this.isSystem = isSystem;
        this.absolutePath = absolutePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    @Override
    public String toString() {
        return "AudioEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", path='" + path + '\'' +
                ", isSystem=" + isSystem +
                ", absolutePath='" + absolutePath + '\'' +
                '}';
    }
}
