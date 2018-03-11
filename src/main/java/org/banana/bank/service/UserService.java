package org.banana.bank.service;

import org.banana.bank.domain.Transaction;
import org.banana.bank.repository.UserRepository;
import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.banana.bank.domain.User;
import org.banana.bank.domain.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User increaseBalance(UUID userId, BigDecimal value) {
        User user = userRepository.getOne(userId);
        BigDecimal newValue = user.getBalance().add(value);

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.INCREASE);
        transaction.setUser(user);
        transaction.setValue(value);

        user.setBalance(newValue);
        user.addTransaction(transaction);
        return userRepository.save(user);
    }

    public String createToken(UUID userId) {
        String secret = Base32.random();
        User user = userRepository.getOne(userId);
        user.setSeedOfToken(secret);

        Totp generator = new Totp(secret);
        return generator.now();
    }

    public void decreaseBalance(UUID userId, BigDecimal value, String token) {
        User user = userRepository.getOne(userId);
        Totp generator = new Totp(user.getSeedOfToken());
        if (generator.verify(token)) {
            BigDecimal newValue = user.getBalance().subtract(value);

            Transaction transaction = new Transaction();
            transaction.setType(TransactionType.DECREASE);
            transaction.setUser(user);
            transaction.setValue(value);

            user.setBalance(newValue);
            user.addTransaction(transaction);
            userRepository.save(user);
        }
    }
}
