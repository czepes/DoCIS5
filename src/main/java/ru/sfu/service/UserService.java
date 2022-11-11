package ru.sfu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sfu.entity.Role;
import ru.sfu.entity.User;
import ru.sfu.repository.RoleRepository;
import ru.sfu.repository.UserRepository;

import java.util.Collections;

/**
 * User Details Service
 * @author Agapchenko V.V.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor
     * @param userRepository User Repository
     * @param roleRepository Role Repository
     * @param passwordEncoder Password Encoder
     */
    @Autowired
    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Load User with username form database
     * @param username Username
     * @return Loaded User
     * @throws UsernameNotFoundException If user wasn't found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("User " + username + " not found");

        return user;
    }

    /**
     * Register new User in the database
     * @param user User to save
     * @return Registration result
     */
    public boolean saveUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser != null)
            return false;

        user.setRoles(
                Collections.singleton(
                        roleRepository
                                .findById(user.getRegRole())
                                .orElse(new Role(1, "ROLE_USER"))
                )
        );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}
