package com.szebi.optimization.repository;

import com.szebi.optimization.model.UserPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Long> {
    // Spring Data JPA automatycznie dostarczy implementację metod takich jak findAll(), save(), etc.
}