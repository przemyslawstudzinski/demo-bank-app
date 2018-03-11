package org.banana.bank;

import org.banana.bank.domain.User;
import org.banana.bank.exception.BadTokenException;
import org.banana.bank.repository.UserRepository;
import org.banana.bank.service.UserService;
import org.jboss.aerogear.security.otp.Totp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private static final UUID ID = UUID.randomUUID();

    private User user;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserService();
        user = new User();
        user.setId(ID);
        user.setBalance(new BigDecimal(0));
        MockitoAnnotations.initMocks(this);
        when(userRepository.getOne(ID)).thenReturn(user);
    }

    @Test
    public void shouldIncreaseBalance() {
        Assert.assertEquals(user.getBalance().doubleValue(), 0, 0);

        when(userRepository.save(user)).thenReturn(user);
        User updated = userService.increaseBalance(ID, new BigDecimal(100.10));
        Assert.assertEquals(new BigDecimal(100.10).doubleValue(),
                updated.getBalance().doubleValue(), 0.000001);
        verify(userRepository, times(1)).getOne(ID);
    }

    @Test
    public void shouldDecreaseBalance() {
        Assert.assertEquals(user.getBalance().doubleValue(), 0, 0);
        user.setBalance(new BigDecimal(100.10));

        user.setSeedOfToken("AAAAAABBBBBBBB");
        String token = userService.createToken(ID);

        when(userRepository.save(user)).thenReturn(user);
        userService.decreaseBalance(ID, new BigDecimal(50.10), token);

        Assert.assertEquals(new BigDecimal(50).doubleValue(),
                user.getBalance().doubleValue(), 0.000001);
        verify(userRepository, times(2)).getOne(ID);
    }

    @Test
    public void shouldGenerateValidToken() {
        user.setSeedOfToken("AAAAAABBBBBBBB");
        String token = userService.createToken(ID);

        Totp newGenerator = new Totp(user.getSeedOfToken());
        boolean result = newGenerator.verify(token);

        Assert.assertTrue(result);

        result = newGenerator.verify("123456");
        Assert.assertFalse(result);
    }

    @Test(expected = BadTokenException.class)
    public void shouldThrowWhenInValidToken() {
        user.setSeedOfToken("AAAAAABBBBBBBB");
        userService.decreaseBalance(ID, new BigDecimal(50.10), "123456");
    }
}

