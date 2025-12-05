package com.szebi.optimization.repository;

import com.szebi.optimization.model.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    // Spring Data JPA dostarcza metody findAll(), save(), findById() itd.
}