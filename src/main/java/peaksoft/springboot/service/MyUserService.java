//package peaksoft.springboot.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import peaksoft.springboot.entity.Role;
//import peaksoft.springboot.entity.User;
//import peaksoft.springboot.repository.RoleRepository;
//import peaksoft.springboot.repository.UserRepository;
//@Component
//@RequiredArgsConstructor
//public class UserService {
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public User saveUser(User user) {
//        Role userRole = roleRepository.findByRoleName("ROLE_ADMIN");
//        user.setRole(userRole);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }
//
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
//
//    }
//
//    public User findByLoginAndPassword(String email, String password) {
//        User user = findByEmail(email);
//        if (user != null) {
//            if (passwordEncoder.matches(password, user.getPassword())) ;
//            return user;
//        }
//        return null;
//    }
//}
