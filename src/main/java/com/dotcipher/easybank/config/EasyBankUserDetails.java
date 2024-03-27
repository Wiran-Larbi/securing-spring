package com.dotcipher.easybank.config;

import com.dotcipher.easybank.model.Agent;
import com.dotcipher.easybank.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EasyBankUserDetails implements UserDetailsService {
    AgentRepository agentRepository;

    public EasyBankUserDetails(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String email, password = null;
        List<GrantedAuthority> authorities = null;
        List<Agent> agent = agentRepository.findByEmail(username);

        if (agent.size() == 0) {
            throw new UsernameNotFoundException("User details not found for the agent : " + username);
        } else {
            email = agent.get(0).getEmail();
            password = agent.get(0).getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(agent.get(0).getRole()));
        }

        return new User(email, password, authorities);
    }
}
