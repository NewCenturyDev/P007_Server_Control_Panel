package com.newcentury99.servercp.domain.dashboard.node.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NodeRepository extends JpaRepository<Node, Long> {
    Optional<Node> findById(Long id);
    List<Node> findAllByOrderByIdAsc();
}
