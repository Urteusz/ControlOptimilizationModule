package com.szebi.optimization.service;

import com.szebi.optimization.model.entity.UserPreferences;
import com.szebi.optimization.repository.UserPreferencesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPreferencesService {

    private final UserPreferencesRepository repository;

    // Wstrzykiwanie zależności przez konstruktor
    public UserPreferencesService(UserPreferencesRepository repository) {
        this.repository = repository;
    }

    /**
     * Pobiera listę wszystkich preferencji użytkowników z bazy danych.
     * Wykorzystywane przez OptimizationController do grupowania użytkowników po miejscach.
     */
    public List<UserPreferences> getAllUserPreferencesList() {
        return repository.findAll();
    }

    /**
     * Dodaje lub aktualizuje preferencje użytkownika.
     */
    public void saveUserPreferences(UserPreferences userPreferences) {
        repository.save(userPreferences);
    }

    /**
     * Pobiera preferencje konkretnego użytkownika po ID (opcjonalnie).
     */
    public Optional<UserPreferences> getUserPreferencesById(Long id) {
        return repository.findById(id);
    }

    // Metoda zgodna ze starym kodem, jeśli gdzieś jeszcze była używana mapa (opcjonalnie do usunięcia)
    // public Map<Place, Map<String, Object>> getAllUserPreferences() { ... }
}