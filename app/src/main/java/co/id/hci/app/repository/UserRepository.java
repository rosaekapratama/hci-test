package co.id.hci.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.id.hci.app.model.User;

/**
 * @author rosaekapratama@gmail.com
 *
 */
public interface UserRepository extends JpaRepository<User, String> {

}
