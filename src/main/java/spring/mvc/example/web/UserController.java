package spring.mvc.example.web;

import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import spring.mvc.example.domain.User;
import spring.mvc.example.dto.BalanceDto;
import spring.mvc.example.dto.TransactionDto;
import spring.mvc.example.mapper.TransactionMapper;
import spring.mvc.example.mapper.UserMapper;
import spring.mvc.example.repository.UserRepository;
import spring.mvc.example.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private UserMapper userMapper = UserMapper.INSTANCE;
    private TransactionMapper transactionMapper = TransactionMapper.INSTANCE;

    @RequestMapping(value = "/balance/user/{userId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BalanceDto getBalance(@PathVariable("userId") UUID userId) {
        User user = userRepository.getOne(userId);
        return userMapper.toBalanceDto(user);
    }

    @RequestMapping(value = "/balance/increase/user/{userId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void increaseBalance(@PathVariable("userId") UUID userId,
                                      @RequestBody BalanceDto balanceDto) {
        userService.increaseBalance(userId, balanceDto.getValue());
    }

    @RequestMapping(value = "/history/user/{userId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<TransactionDto> getHistory(@PathVariable("userId") UUID userId) {
        User user = userRepository.getOne(userId);
        return transactionMapper.toTransactionDtos(user.getTransactions());
    }
}
