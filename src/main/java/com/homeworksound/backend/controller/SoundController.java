package com.homeworksound.backend.controller;

import com.homeworksound.backend.model.SoundEntity;
import com.homeworksound.backend.service.SoundService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sounds")
public class SoundController {

    private final SoundService soundService;

    @Autowired
    public SoundController(SoundService soundService) {
        this.soundService = soundService;
    }

    @PostMapping("/all")
    @ResponseBody
    public List<SoundEntity> getAllSounds() {
        return soundService.getAllSounds();
    }

    @PostMapping("/get/{id}")
    public @ResponseBody SoundEntity getSoundById(@PathVariable Integer id) {
        return soundService.getSoundById(id);
    }
    
    @PostMapping("/delete/{id}")
    public void  deleteSoundById(@PathVariable Integer id) {
        soundService.deleteById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<SoundEntity> createNewSound(@RequestBody SoundEntity sound) {
        SoundEntity createdSound = soundService.newSound(sound);
        return ResponseEntity.ok(createdSound);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<SoundEntity> updateSound(@PathVariable Integer id, @RequestBody SoundEntity sound) {
        try {
            return ResponseEntity.ok(soundService.updateSoundById(id, sound));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).build();
        }
    }
}