package com.homeworksound.backend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sound")
@Getter
@Setter
public class SoundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer soundId;

    @Getter
    @Column(nullable = false)
    private String soundName;

    @Lob
    @Column( nullable = false)
    private byte[] soundData;

    @Column(nullable = false)
    private String soundExtension;

    @Column( nullable = false)
    private LocalDateTime soundCreatedDatetime;

    private LocalDateTime soundUpdatedDatetime;

    public SoundEntity(String soundName, byte[] soundData, String soundExtension) {

        this.soundName = soundName;
        this.soundData = soundData;
        this.soundExtension = soundExtension;
        this.soundCreatedDatetime = LocalDateTime.now();
    }

    public SoundEntity() {
    }
}