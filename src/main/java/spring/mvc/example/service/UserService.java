package spring.mvc.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.mvc.example.domain.Transaction;
import spring.mvc.example.domain.User;
import spring.mvc.example.domain.enums.OperationType;
import spring.mvc.example.repository.UserRepository;

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
        transaction.setType(OperationType.INCREASE);
        transaction.setUser(user);
        transaction.setValue(value);

        user.setBalance(newValue);
        user.addTransaction(transaction);
        return userRepository.save(user);
    }
}
