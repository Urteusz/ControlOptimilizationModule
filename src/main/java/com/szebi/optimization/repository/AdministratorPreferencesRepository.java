package com.szebi.optimization.repository;

import com.szebi.optimization.model.AdministratorPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorPreferencesRepository extends JpaRepository<AdministratorPreferences, Long> {
    // Standardowe metody JPA wystarczą
}