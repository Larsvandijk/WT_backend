package nl.workingtalent.wtacademy.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.wtacademy.dto.ResponseDto;

@RestController
@CrossOrigin(maxAge = 3600)
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private UserMapper mapper;

	// READ
	@RequestMapping("user/all")
	public ResponseDto findAllUsers() {
		List<User> users = service.findAllUsers();

		return createResponseDtoList(null, users, null);
	}

	@RequestMapping("user/{id}")
	public ResponseDto findUserById(@PathVariable("id") long id) {
		Optional<User> userOptional = service.findUserById(id);

		if (userOptional.isPresent()) {
			return new ResponseDto(false, userOptional, null, null);
		}

		return new ResponseDto(false, null, null, "No user found with id " + id);
	}

	@RequestMapping("user/firstname/{firstName}")
	public ResponseDto findAllUsersByFirstName(@PathVariable("firstName") String name) {
		List<User> users = service.findUserByFirstName(name);

		return createResponseDtoList(name, users, "first name");
	}

	@RequestMapping("user/lastname/{lastName}")
	public ResponseDto findUserByLastName(@PathVariable("lastName") String name) {
		List<User> users = service.findUserByLastName(name);

		return createResponseDtoList(name, users, "last name");

	}

	@RequestMapping("user/email/{email}")
	public ResponseDto findUserByEmail(@PathVariable("email") String email) {
		Optional<User> userOptional = service.findUserByEmail(email);

		if (userOptional.isPresent()) {
			return new ResponseDto(false, userOptional, null, null);
		}

		return new ResponseDto(false, null, null, "No user found with email " + email);
	}

	@RequestMapping("user/role/{role}")
	public ResponseDto findUserByRole(@PathVariable("role") Role role) {
		List<User> users = service.findUserByRole(role);

		return createResponseDtoList(role, users, "role");
	}

	// CREATE
	@PostMapping("user/create")
	public ResponseDto createUser(@RequestBody CreateUserDto dto) {

		// DOES EMAIL EXIST?
		Optional<User> existingUserEmail = service.findUserByEmail(dto.getEmail());
		if (existingUserEmail.isPresent()) {
			ResponseDto responseDto = new ResponseDto(false, existingUserEmail.get().getEmail(), null,
					"User with the provided email already exists.");
			return responseDto;
		}

		User newUser = new User();
		newUser.setFirstName(dto.getFirstName());
		newUser.setLastName(dto.getLastName());
		newUser.setEmail(dto.getEmail());
		newUser.setPassword(dto.getPassword());
		newUser.setRole(dto.getRole());
		service.create(newUser);

		ResponseDto responseDto = new ResponseDto(true, newUser, null, "User created successfully.");
		return responseDto;
	}

	// UPDATE
	@PutMapping("user/update/{id}")
	public ResponseDto updateUser(@RequestBody UpdateUserDto dto, @PathVariable("id") long id) {

		// DOES USER EXIST?
		Optional<User> existingUser = service.findUserById(id);
		if (existingUser.isEmpty()) {
			ResponseDto responseDto = new ResponseDto(false, existingUser, null, "User doesn't exist.");
			return responseDto;
		}

		// DOES EMAIL EXIST?
		Optional<User> existingUserEmail = service.findUserByEmail(dto.getEmail());
		if (existingUserEmail.isPresent() && existingUserEmail.get().getId() != id) {
			ResponseDto responseDto = new ResponseDto(false, existingUserEmail.get().getEmail(), null,
					"User with the provided email already exists.");
			return responseDto;
		}

		User dbUser = existingUser.get();

		// OVERWRITE
		dbUser.setFirstName(dto.getFirstName());
		dbUser.setLastName(dto.getLastName());
		dbUser.setEmail(dto.getEmail());
		dbUser.setPassword(dto.getPassword());
		dbUser.setRole(dto.getRole());

		// SAVE
		service.update(dbUser);
		ResponseDto responseDto = new ResponseDto(true, dto, null, "User updated successfully.");
		return responseDto;
	}

	// DELETE
	@DeleteMapping("user/delete/{id}")
	public ResponseDto deleteUser(@PathVariable("id") long id) {
		Optional<User> user = service.findUserById(id);
		if (user.isEmpty()) {
			ResponseDto responseDto = new ResponseDto(false, user, null, "User doesn't exist.");
			return responseDto;
		}
		service.delete(id);
		ResponseDto responseDto = new ResponseDto(true, null, null, "User deleted successfully.");
		return responseDto;
	}


	// Gets the responseDto for objects who return a list of values
	private ResponseDto createResponseDtoList(Object pathVal, List<User> users, String pathVar) {
		if (users.isEmpty()) {
			ResponseDto responseDto = new ResponseDto(false, pathVal, null,
					"No users with the " + pathVar + " '" + pathVal + "' found.");
			return responseDto;
		}

		Stream<ReadUserDto> readUserDtoStream = mapper.convertUsersToReadUserDtos(users);
		ResponseDto responseDto = new ResponseDto(true, readUserDtoStream, null,
				users.size() + " " + pathVar + " found.");

		return responseDto;
	}
}
