package uk.ac.le.security.impl;

import uk.ac.le.model.User;
import uk.ac.le.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserManager userManager;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        User userInDatabase = userManager.get(username);

        org.springframework.security.core.userdetails.User user = null;
        /**
         * Allow the test account, else check if the user's entered password with the password in database
         */
        if (username.equalsIgnoreCase("admin")) {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            user = new org.springframework.security.core.userdetails.User("admin", "65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5", roles);
        } else {
            user = new org.springframework.security.core.userdetails.User(userInDatabase.getUsername(),
                    userInDatabase.getPassword(), roles);
        }

        return user;
    }

}
