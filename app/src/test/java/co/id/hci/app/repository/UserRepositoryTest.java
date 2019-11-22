package co.id.hci.app.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import co.id.hci.app.repository.UserRepository;

/**
 * @author rosaekapratama@gmail.com
 *
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void noUserFoundTest() {
		assertThrows(NoSuchElementException.class, () -> {
			userRepository.findById("rosaekapratama").get();
		});
		assertThat(userRepository.findById("rosaekapratama").isEmpty()).isTrue();
	}

	@Test
	public void whenFindByUsername_thenReturnUser() {
		assertThat(userRepository.findById("tamama")).isNotNull();
	}
}
