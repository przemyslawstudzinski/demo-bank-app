package org.banana.bank.web;

import org.banana.bank.dto.DecreaseDto;
import org.banana.bank.dto.TokenDto;
import org.banana.bank.exception.ValidationException;
import org.banana.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.banana.bank.domain.User;
import org.banana.bank.dto.BalanceDto;
import org.banana.bank.dto.TransactionDto;
import org.banana.bank.mapper.TransactionMapper;
import org.banana.bank.mapper.UserMapper;
import org.banana.bank.service.UserService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Controller responsible for processing requests of Users.
 */
@Transactional
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private UserMapper userMapper = UserMapper.INSTANCE;

    private TransactionMapper transactionMapper = TransactionMapper.INSTANCE;

    /**
     * Returns the current balance value for User.
     */
    @RequestMapping(value = "/balance/user/{userId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BalanceDto getBalance(@PathVariable(value = "userId", required = false) UUID userId) {

        final User user = userRepository.getOne(userId);
        return userMapper.toBalanceDto(user);
    }

    /**
     * Allows increase balance operation for User.
     */
    @RequestMapping(value = "/balance/increase/user/{userId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void increaseBalance(@PathVariable("userId") UUID userId,
                                @RequestBody @Valid BalanceDto balanceDto,
                                BindingResult bindingResult) {
        checkBindingResult(bindingResult);
        userService.increaseBalance(userId, balanceDto.getValue());
    }

    /**
     * Returns the history of the transactions for User.
     */
    @RequestMapping(value = "/history/user/{userId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<TransactionDto> getHistory(@PathVariable("userId") UUID userId) {
        final User user = userRepository.getOne(userId);
        return transactionMapper.toTransactionDtos(user.getTransactions());
    }

    /**
     * Allows create one time password that can be used to secure other operations.
     */
    @RequestMapping(value = "/tokens/user/{userId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TokenDto createToken(@PathVariable("userId") UUID userId) {
        final String token = userService.createToken(userId);
        return new TokenDto(token);
    }

    /**
     * Allows decrease balance operation for User.
     */
    @RequestMapping(value = "/balance/decrease/user/{userId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void decreaseBalance(@PathVariable("userId") UUID userId,
                                @RequestBody @Valid DecreaseDto decreaseDto,
                                BindingResult bindingResult) {
        checkBindingResult(bindingResult);
        userService.decreaseBalance(userId, decreaseDto.getValue(), decreaseDto.getToken());
    }

    private void checkBindingResult(BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
    }
}
