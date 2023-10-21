package in.aesl.burlyeducation.service;

import in.aesl.burlyeducation.entity.User;
import in.aesl.burlyeducation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User student) {
        return userRepository.save(student);
    }

    public User getDetails(String username) {
        return userRepository.findByUname(username);
    }

    public String getUserRoles(String username) {
        return userRepository.findByUname(username).getSrole();
    }
}
