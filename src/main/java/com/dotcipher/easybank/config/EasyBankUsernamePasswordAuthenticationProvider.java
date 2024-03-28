package com.dotcipher.easybank.config;

import com.dotcipher.easybank.model.Agent;
import com.dotcipher.easybank.repository.AgentRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EasyBankUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private AgentRepository agentRepository;
    private PasswordEncoder passwordEncoder;

    public EasyBankUsernamePasswordAuthenticationProvider(AgentRepository agentRepository, PasswordEncoder passwordEncoder){
        this.agentRepository = agentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Fetch user details from database
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<Agent> agent = agentRepository.findByEmail(username);

        if (agent.size() > 0) {
            if (passwordEncoder.matches(password, agent.get(0).getPassword())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(agent.get(0).getRole()));
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            } else {
                throw new BadCredentialsException("Invalid password");
            }
        }else {
            throw new BadCredentialsException("No agent registered with the email : " + username);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
