package com.example.multimodule.application.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.library.entity.Users;
import com.example.library.repository.UserRepository;

/**
 * The Class UserPrincipalDetailsService.
 */
@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    
    /** The user repository. */
    private UserRepository userRepository;

    /**
     * Instantiates a new user principal details service.
     *
     * @param userRepository the user repository
     */
    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = this.userRepository.findByName(s);
        if(user == null) {
        	throw new UsernameNotFoundException(s);
        }
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
    }
}