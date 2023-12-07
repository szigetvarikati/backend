package com.homeworksound.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homeworksound.backend.model.SoundEntity;
import com.homeworksound.backend.repository.SoundRepository;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SoundIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SoundRepository soundRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private List<SoundEntity> testData;

    @BeforeEach
    public void setUp() {
        testData = Arrays.asList(
                new SoundEntity("First Sound", new byte[]{}, "mp3"),
                new SoundEntity("Second Sound", new byte[]{}, "wav"),
                new SoundEntity("Third Sound", new byte[]{}, "wma")
        );
        soundRepository.saveAll(testData);
    }

    @AfterEach
    public void tearDown() {
        soundRepository.deleteAll();
    }

    @Test
    public void testListAllSounds() throws Exception {
        mockMvc.perform(post("/api/sounds/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].soundId").value(1))
                .andExpect(jsonPath("$[0].soundName").value("First Sound"))
                .andExpect(jsonPath("$[0].soundExtension").value("mp3"))
                .andExpect(jsonPath("$[1].soundId").value(2))
                .andExpect(jsonPath("$[1].soundName").value("Second Sound"))
                .andExpect(jsonPath("$[1].soundExtension").value("wav"))
                .andExpect(jsonPath("$[2].soundId").value(3))
                .andExpect(jsonPath("$[2].soundName").value("Third Sound"))
                .andExpect(jsonPath("$[2].soundExtension").value("wma"));

        List<SoundEntity> allSounds = soundRepository.findAll();
        assertThat(allSounds).hasSize(testData.size());
    }

    @Test
    public void testGetSoundById() throws Exception {
        mockMvc.perform(post("/api/sounds/get/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.soundId").value("1"))
                .andExpect(jsonPath("$.soundName").value("First Sound"))
                .andExpect(jsonPath("$.soundExtension").value("mp3"));
    }

    @Test
    public void testDeleteSoundById() throws Exception {
        mockMvc.perform(post("/api/sounds/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateNewSound() throws Exception {
        SoundEntity newSound = new SoundEntity();
        newSound.setSoundName("New Sound");
        newSound.setSoundData(new byte[]{1, 2, 3});
        newSound.setSoundExtension("mp3");

        mockMvc.perform(post("/api/sounds/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSound)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.soundName").value("New Sound"))
                .andExpect(jsonPath("$.soundExtension").value("mp3"));

        List<SoundEntity> allSounds = soundRepository.findAll();
        assertThat(allSounds).hasSize(testData.size() + 1);
    }

    @Test
    public void testUpdateSound() throws Exception {
        SoundEntity updatedSound = new SoundEntity();
        updatedSound.setSoundName("Updated Sound");
        updatedSound.setSoundData(new byte[]{4, 5, 6});
        updatedSound.setSoundExtension("mp3");

        mockMvc.perform(post("/api/sounds/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedSound)))
                .andExpect(status().isOk());

        SoundEntity soundEntity = soundRepository.findById(1).orElse(null);
        assertThat(soundEntity).isNotNull();
        assertThat(soundEntity.getSoundName()).isEqualTo("Updated Sound");
        assertThat(soundEntity.getSoundExtension()).isEqualTo("mp3");

        List<SoundEntity> allSounds = soundRepository.findAll();
        assertThat(allSounds).hasSize(testData.size());
    }
}
