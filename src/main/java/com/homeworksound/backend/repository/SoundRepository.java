package com.homeworksound.backend.repository;
import com.homeworksound.backend.model.SoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoundRepository extends JpaRepository<SoundEntity, Integer> {
}
