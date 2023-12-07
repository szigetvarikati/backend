package com.homeworksound.backend.service;

import com.homeworksound.backend.model.SoundEntity;
import com.homeworksound.backend.repository.SoundRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SoundService {

    private final SoundRepository soundRepository;

    @Autowired
    public SoundService(SoundRepository soundRepository) {
        this.soundRepository = soundRepository;
    }

    public List<SoundEntity> getAllSounds() {
        return soundRepository.findAll();
    }

    public SoundEntity getSoundById(Integer id) {
        return soundRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public SoundEntity newSound(SoundEntity entity) {
        entity.setSoundCreatedDatetime(LocalDateTime.now());
        return soundRepository.save(entity);
    }

    public void deleteById(Integer id) {
        soundRepository.deleteById(id);
    }

    @Transactional
    public SoundEntity updateSoundById(Integer id, SoundEntity sound) {
        SoundEntity soundToUpdate = soundRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (sound.getSoundName() != null) {
            soundToUpdate.setSoundName(sound.getSoundName());
        }
        if (sound.getSoundData() != null) {
            soundToUpdate.setSoundData(sound.getSoundData());
        }
        if (sound.getSoundExtension() != null) {
            soundToUpdate.setSoundExtension(sound.getSoundExtension());
        }
        soundToUpdate.setSoundUpdatedDatetime(LocalDateTime.now());
        soundRepository.save(soundToUpdate);
        return soundToUpdate;
    }
}