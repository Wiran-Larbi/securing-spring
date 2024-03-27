package com.dotcipher.easybank.repository;

import com.dotcipher.easybank.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Integer> {
    List<Agent> findByEmail(String email);
}
