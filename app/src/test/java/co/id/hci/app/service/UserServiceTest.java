package co.id.hci.app.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.id.hci.app.model.User;
import co.id.hci.app.repository.UserRepository;


/**
 * @author rosaekapratama@gmail.com
 *
 */
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
	
	@TestConfiguration
    static class UserServiceTestContextConfiguration {
  
        @Bean
        public UserService userService() {
            return new UserService();
        }
    }
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@BeforeEach
	public void setup() {
		User tamama = new User();
		tamama.setUsername("tamama");
		Mockito.when(userRepository.findById(tamama.getUsername())).thenReturn(Optional.of(tamama));
	}
	
	@Test
	public void returnNullWhenNoSuchElementThrown() {
		assertThat(userService.findById("rosaekapratama")).isNull();
	}

	@Test
	public void whenFindByUsername_thenReturnUser() {
		assertThat(userService.findById("tamama")).isNotNull();
	}
}
