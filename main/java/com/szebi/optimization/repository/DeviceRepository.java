package com.szebi.optimization.repository;

import com.szebi.optimization.model.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    // Spring Data JPA dostarcza metody findAll(), save(), findById() itd.
}