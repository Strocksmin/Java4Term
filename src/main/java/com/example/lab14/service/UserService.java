package com.example.lab14.service;

import com.example.lab14.data.UserData;
import com.example.lab14.exception.UserAlreadyExistsException;
import com.example.lab14.model.User;
import com.example.lab14.model.UserGroup;
import com.example.lab14.repository.UserGroupRepository;
import com.example.lab14.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserGroupRepository userGroupRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        if ((userGroupRepository.findByCode("user") == null) && (userGroupRepository.findByCode("admin") == null)) {
            userGroupRepository.save(new UserGroup("user", "user"));
            userGroupRepository.save(new UserGroup("admin", "admin"));
        }
    }

    public void register(UserData user) throws UserAlreadyExistsException {
        if(checkIfUserExist(user.getEmail())){
            throw new UserAlreadyExistsException("User already exists for this email");
        }
        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);
        encodePassword(userEntity, user);
        updateUserGroup(userEntity);
        userRepository.save(userEntity);
    }

    private void updateUserGroup(User user){
        UserGroup userGroup = userGroupRepository.findByCode("user");
        user.addUserGroups(userGroup);
    }

    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    private void encodePassword(User userEntity, UserData user){
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }
}
