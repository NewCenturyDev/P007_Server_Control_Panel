package com.newcentury99.servercp.domain.dashboard.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DaemonRepository extends JpaRepository<Daemon, Long> {
    Optional<Daemon> findById(Long id);
    List<Daemon> findAllByOrderByNameAsc();
}
