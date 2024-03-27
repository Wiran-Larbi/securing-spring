package com.dotcipher.easybank.controller;

import com.dotcipher.easybank.model.Agent;
import com.dotcipher.easybank.repository.AgentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    AgentRepository agentRepository;

    public RegisterController(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerAgent(@RequestBody Agent agent){
        Agent savedAgent = null;
        ResponseEntity response = null;

        try {
            savedAgent = agentRepository.save(agent);
            if (savedAgent.getId() > 0){
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Agent registered successfully");
            }
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body("Agent registration failed");
        }

        return response;
    }


}
