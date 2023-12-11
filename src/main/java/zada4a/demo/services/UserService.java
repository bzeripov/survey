package zada4a.demo.services;

import zada4a.demo.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Users registerUser(Users user);

    Users updateAvatar(Users user);
    boolean updatePassword(Users user, String oldPassword, String newPassword);

}
