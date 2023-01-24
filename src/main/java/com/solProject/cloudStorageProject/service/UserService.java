package com.solProject.cloudStorageProject.service;

import com.solProject.cloudStorageProject.mapper.UserMapper;
import com.solProject.cloudStorageProject.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class UserService {
    private UserMapper userMapper;
    private HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }
    public boolean usernameIsAvailable(String username) {
        return userMapper.getUser(username) == null;
    }
    public Integer createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        String encodeSalt = Base64.getEncoder().encodeToString(salt);

        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodeSalt);
        user.setSalt(encodeSalt);
        user.setPassword(hashedPassword);

        return userMapper.create(user);
    }
    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}

/**
 * Encryption: Modifying data before storing it, with the intention of using another algorithm to return the data to its original form once it needs to be used.
 * Hashing: Modifying data before storing with the intention of never returning it to its original form. The modified data will be compared to other modified data only.
 * Salt: random data that is combined with the input string when hashing so that the resultant hashed values are unique for each row.
 * This means that two users with the same password would not have the same hash in the database.
 */
