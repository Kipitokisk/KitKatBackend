package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Optional<Language> findByLanguageName(String name);
    Language findByLanguageId(Integer id);
}
