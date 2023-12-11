package zada4a.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zada4a.demo.entities.Roles;
import zada4a.demo.entities.Survey;
import zada4a.demo.entities.Users;
import zada4a.demo.repositories.RoleRepository;
import zada4a.demo.repositories.SurveyRepository;
import zada4a.demo.repositories.UserRepository;
import zada4a.demo.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email);
        if(user!=null){
            return user;
        }
        else{
            throw new UsernameNotFoundException("USER NOT FOUND");
        }
    }

    @Override
    public Users registerUser(Users user) {

        Users checkUser = userRepository.findByEmail(user.getEmail());
        if(checkUser==null){

            Roles userRole = rolesRepository.findByRole("ROLE_USER");
            List<Roles> roles = new ArrayList<>();
            roles.add(userRole);
            user.setRoles(roles);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }

        return null;

    }

    @Autowired
    SurveyRepository themeRepository;

    @Override
    public boolean updatePassword(Users user, String oldPassword, String newPassword) {
        Optional<Users> checkUser = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));

        if (checkUser.isPresent() && passwordEncoder.matches(oldPassword, checkUser.get().getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken)) {
            return (Users) authentication.getPrincipal();
        }
        return null;
    }
    public Survey addTheme(Survey theme) {
        Users currentUser = getUser();

        if (currentUser != null) {
            theme.setAuthor(currentUser);
            return themeRepository.save(theme);
        }

        return null;
    }

    @Override
    public Users updateAvatar(Users user) {
        Users checUser = userRepository.findByEmail(user.getEmail());

        if(checUser!=null){
            checUser.setAvatar(user.getAvatar());
            return userRepository.save(checUser);
        }

        return null;

    }
}
