package com.szebi.optimization.service;

import com.szebi.optimization.model.entity.UserPreferences;
import com.szebi.optimization.repository.UserPreferencesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPreferencesService {

    private final UserPreferencesRepository repository;

    public UserPreferencesService(UserPreferencesRepository repository) {
        this.repository = repository;
    }

    public List<UserPreferences> getAllUserPreferencesList() {
        return repository.findAll();
    }


    public void saveUserPreferences(UserPreferences userPreferences) {
        repository.save(userPreferences);
    }

    public Optional<UserPreferences> getUserPreferencesById(Long id) {
        return repository.findById(id);
    }
}