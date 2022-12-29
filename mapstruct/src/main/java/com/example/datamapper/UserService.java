package com.example.datamapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {
   private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public List<User> findAll(){
//    return userRepository.findAll();
//    }
//    public void deleteById(Long id) {
//        userRepository.deleteById(id);
//    }

    public User getUser(Long id) {
    return  userRepository.getReferenceById(id);
    }
}
