package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Language findByLanguageName(String name);
}
