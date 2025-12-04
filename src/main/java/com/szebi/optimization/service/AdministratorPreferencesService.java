package com.szebi.optimization.service;

import com.szebi.optimization.model.AdministratorPreferences;
import com.szebi.optimization.repository.AdministratorPreferencesRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class AdministratorPreferencesService {

    private final AdministratorPreferencesRepository repository;

    public AdministratorPreferencesService(AdministratorPreferencesRepository repository) {
        this.repository = repository;
    }

    public AdministratorPreferences getAdministratorPreferences() {
        List<AdministratorPreferences> allPrefs = repository.findAll();

        if (allPrefs.isEmpty()) {
            AdministratorPreferences defaultPrefs = new AdministratorPreferences(
                    18.0,
                    26.0,
                    Time.valueOf("08:00:00"),
                    Time.valueOf("22:00:00"),
                    1000.0,
                    1
            );
            return repository.save(defaultPrefs);
        }

        return allPrefs.get(0);
    }

    public AdministratorPreferences updateAdministratorPreferences(AdministratorPreferences newPrefs) {
        AdministratorPreferences currentPrefs = getAdministratorPreferences();

        currentPrefs.setPrefferedMinTemperature(newPrefs.getPrefferedMinTemperature());
        currentPrefs.setPrefferedMaxTemperature(newPrefs.getPrefferedMaxTemperature());
        currentPrefs.setTimeOpen(newPrefs.getTimeOpen());
        currentPrefs.setTimeClose(newPrefs.getTimeClose());
        currentPrefs.setMaxEnergyUsage(newPrefs.getMaxEnergyUsage());
        currentPrefs.setPriorityComfort(newPrefs.getPriorityComfort());

        return repository.save(currentPrefs);
    }
}