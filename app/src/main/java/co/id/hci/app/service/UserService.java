package co.id.hci.app.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.hci.app.model.User;
import co.id.hci.app.repository.UserRepository;

/**
 * @author rosaekapratama@gmail.com
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository r;
	
	public User findById(String username) {
		try {
			return r.findById(username).get();
		}catch(NoSuchElementException e) {
			return null;
		}
	}
	
	public User save(User user) {
		return r.save(user);
	}
	
	public void delete(User user) {
		r.delete(user);
	}
	
}
