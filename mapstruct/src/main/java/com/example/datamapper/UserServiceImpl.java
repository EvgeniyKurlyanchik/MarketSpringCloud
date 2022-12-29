package com.example.datamapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
   private final UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }
}


//    public List<User> findAll(){
//    return userRepository.findAll();
//    }
//    public void deleteById(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    public User getUser(Long id) {
//    return  userRepository.getReferenceById(id);
//    }

