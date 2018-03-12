package org.banana.bank.service;

import org.banana.bank.domain.Transaction;
import org.banana.bank.exception.BadTokenException;
import org.banana.bank.repository.UserRepository;
import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.banana.bank.domain.User;
import org.banana.bank.domain.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

import static org.banana.bank.constant.ValidationMessages.BAD_TOKEN;

/**
 * Stereotype for business logic layer about Users.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Allows increase balance for User.
     *
     * @param userId Id of User
     * @param value increase value
     *
     * @return updated User
     */
    public User increaseBalance(UUID userId, BigDecimal value) {
        User user = userRepository.getOne(userId);
        BigDecimal newBalance = user.getBalance().add(value);

        return updateBalance(value, user, newBalance, TransactionType.INCREASE);
    }

    /**
     * Allows create token.
     *
     * @param userId Id of User
     *
     * @return token
     */
    public String createToken(UUID userId) {
        String secret = Base32.random();
        User user = userRepository.getOne(userId);
        user.setSeedOfToken(secret);

        Totp generator = new Totp(secret);
        return generator.now();
    }

    /**
     * Allows decrease balance for User.
     *
     * @param userId Id of User
     * @param value decrease value
     * @param token valid token
     *
     * @throws BadTokenException when token invalid
     *
     * @return updated User
     */
    public void decreaseBalance(UUID userId, BigDecimal value, String token) throws BadTokenException {
        User user = userRepository.getOne(userId);
        Totp generator = new Totp(user.getSeedOfToken());
        if (generator.verify(token)) {
            BigDecimal newBalance = user.getBalance().subtract(value);

            updateBalance(value, user, newBalance, TransactionType.DECREASE);
        } else {
            throw new BadTokenException(BAD_TOKEN);
        }
    }

    private User updateBalance(BigDecimal value, User user, BigDecimal newBalance, TransactionType type) {
        Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setUser(user);
        transaction.setValue(value);

        user.setBalance(newBalance);
        user.addTransaction(transaction);
        return userRepository.save(user);
    }
}
