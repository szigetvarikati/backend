package com.homeworksound.backend.service;

import com.homeworksound.backend.model.SoundEntity;
import com.homeworksound.backend.repository.SoundRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class SoundServiceTest {
    @Mock
    private SoundRepository repository;
    private SoundService underTest;
    private List<SoundEntity> testData;

    @BeforeEach
    public void setUp() {
        underTest = new SoundService(repository);
        initializeTestData();
    }

    private void initializeTestData() {
        testData = new ArrayList<>();
        testData.add(new SoundEntity(1, "First Sound", new byte[]{}, "mp3"));
        testData.add(new SoundEntity(2,"Second Sound", new byte[]{}, "waw"));
        testData.add(new SoundEntity(10,"Tenth Sound", new byte[]{}, "wma"));
    }
    @Test
    public void getAllSound_test() {
        Mockito.when(repository.findAll()).thenReturn(testData);
        List<SoundEntity> result = underTest.getAllSounds();
        int expectedResultSize = testData.size();
        assertEquals(expectedResultSize, result.size());
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10})
    public void getSoundById_shouldReturnEntity(int id) {
        SoundEntity expectedEntity = new SoundEntity();
        expectedEntity.setSoundId(id);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(expectedEntity));

        SoundEntity result = underTest.getSoundById(id);

        assertNotNull(result);
        assertEquals(expectedEntity, result);
        assertEquals(expectedEntity.getSoundId(), result.getSoundId());
    }

    @Test
    public void getSoundById_test_shouldBeFailed_withNonExistingID() {
        int id = 50;
        Optional<SoundEntity> optionalEntity = testData.stream()
                .filter(soundEntity -> {
                    Integer soundId = soundEntity.getSoundId();
                    return soundId != null && soundId.equals(id);
                })
                .findFirst();
        assertFalse(optionalEntity.isPresent());

        Mockito.when(repository.findById(id)).thenReturn(Optional.ofNullable(optionalEntity.orElse(null)));

        assertThrows(EntityNotFoundException.class, () -> underTest.getSoundById(id));
    }

    @Test
    public void createNewSound_shouldSaveAndReturnEntity() {
        SoundEntity inputEntity = new SoundEntity(33, "New sound", new byte[]{}, "mp3");
        SoundEntity savedEntity;

        Mockito.when(repository.save(inputEntity)).thenReturn(inputEntity);

        savedEntity = underTest.newSound(inputEntity);

        assertNotNull(savedEntity);
        assertEquals(inputEntity.getSoundId(), savedEntity.getSoundId());
        assertEquals(inputEntity.getSoundName(), savedEntity.getSoundName());
        assertArrayEquals(inputEntity.getSoundData(), savedEntity.getSoundData());
        assertEquals(inputEntity.getSoundExtension(), savedEntity.getSoundExtension());

        Mockito.verify(repository, Mockito.times(1)).save(inputEntity);
    }

    @Test
    public void deleteById_shouldDeleteExistingEntity() {
        Integer existingId = 1;

        underTest.deleteById(existingId);

        Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
    }

    @Test
    public void deleteById_shouldNotThrowExceptionForNonExistingEntity() {
        Integer nonExistingId = 100;
        assertDoesNotThrow(() -> underTest.deleteById(nonExistingId));
    }

    @Test
    public void updateSoundById_shouldUpdateExistingEntity() {
        Integer existingId = 1;
        SoundEntity updatedSound = new SoundEntity(existingId, "New Sound", new byte[]{}, "waw");

        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(testData.get(0)));

        SoundEntity result = underTest.updateSoundById(existingId, updatedSound);

        assertNotNull(result);
        assertEquals(updatedSound.getSoundName(), result.getSoundName());
        assertEquals(updatedSound.getSoundData(), result.getSoundData());
        assertEquals(updatedSound.getSoundExtension(), result.getSoundExtension());
        assertNotNull(result.getSoundUpdatedDatetime());

        Mockito.verify(repository, Mockito.times(1)).findById(existingId);
        Mockito.verify(repository, Mockito.times(1)).save(testData.get(0));
    }

    @Test
    public void updateSoundById_shouldThrowExceptionForNonExistingEntity(){
        Integer nonExistingId = 111;
        SoundEntity updatedSound = new SoundEntity(nonExistingId, "New Sound", new byte[]{}, "waw");

        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> underTest.updateSoundById(nonExistingId, updatedSound));

        Mockito.verify(repository, Mockito.times(1)).findById(nonExistingId);
        Mockito.verify(repository, Mockito.never()).save(any());
    }
}