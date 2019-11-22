package co.id.hci.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.id.hci.app.model.Group;
import co.id.hci.app.model.User;
import co.id.hci.app.service.UserService;

/**
 * @author rosaekapratama@gmail.com
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "/u/{username}/modules", method = RequestMethod.GET)
	public ResponseEntity<Group> getModulesByUsername(@PathVariable(name = "username") String username) {
		User user = userService.findById(username);
		if(user==null)
			return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
		else {
			Group group = user.getGroup();
			if(group==null)
				return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<Group>(group, HttpStatus.OK);
		}
	}
	
}
