package com.szebi.optimization.config;

import com.szebi.optimization.model.entity.*;
import com.szebi.optimization.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Time;

@Component
public class DataSeeder implements CommandLineRunner {

    private final DeviceRepository deviceRepository;
    private final UserPreferencesRepository userPreferencesRepository;
    private final AdministratorPreferencesRepository adminRepository;
    private final PlaceRepository placeRepository;

    public DataSeeder(DeviceRepository deviceRepository,
                      UserPreferencesRepository userPreferencesRepository,
                      AdministratorPreferencesRepository adminRepository,
                      PlaceRepository placeRepository) {
        this.deviceRepository = deviceRepository;
        this.userPreferencesRepository = userPreferencesRepository;
        this.adminRepository = adminRepository;
        this.placeRepository = placeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (adminRepository.count() > 0) return; // Jeśli dane są, nie dodawaj ponownie

        System.out.println("--- ROZPOCZYNAM SEEDOWANIE DANYCH TESTOWYCH ---");

        // 1. Preferencje Admina (Widełki: 18°C - 24°C)
        AdministratorPreferences adminPref = new AdministratorPreferences(
                18.0, 24.0,
                Time.valueOf("08:00:00"), Time.valueOf("16:00:00"),
                1000.0, 5
        );
        adminRepository.save(adminPref);

        // 2. Miejsce: Salon
        Place salon = new Place("Salon");
        // Upewnij się, że Place ma konstruktor lub settery
        placeRepository.save(salon);

        // 3. Urządzenia w Salonie
        // Grzejnik ustawiony na start na 20 stopni
        HeatingDevice heater = new HeatingDevice("Grzejnik Główny", salon, 500.0, 20.0);
        // Lampa ustawiona na start na 50%
        LightingDevice lamp = new LightingDevice("Lampa Sufitowa", salon, 60.0, 50);

        deviceRepository.save(heater);
        deviceRepository.save(lamp);

        // 4. Użytkownicy w Salonie (Skala 0-5)
        // Użytkownik A: Zmarzluch (chce ciepło - 5), lubi ciemno (1)
        UserPreferences userA = new UserPreferences(5, 1, "Salon");

        // Użytkownik B: Gorąco mu (chce zimno - 1), lubi jasno (5)
        UserPreferences userB = new UserPreferences(1, 5, "Salon");

        // Użytkownik C: Jest w kuchni (nie powinien wpłynąć na Salon)
        UserPreferences userC = new UserPreferences(5, 5, "Kuchnia");

        userPreferencesRepository.save(userA);
        userPreferencesRepository.save(userB);
        userPreferencesRepository.save(userC);

        System.out.println("--- DANE TESTOWE ZAŁADOWANE ---");
    }
}