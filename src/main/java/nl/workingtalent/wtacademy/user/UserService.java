package nl.workingtalent.wtacademy.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private IUserRepository repository;

	// READ
	public List<User> findAllUsers() {
		return repository.findAll();
	}

	public Optional<User> findUserById(long id) {
		return repository.findById(id);
	}

	public List<User> searchUser(SearchUserDto searchUserDto) {
		String firstName = searchUserDto.getFirstName();
		String lastName = searchUserDto.getLastName();
		String email = searchUserDto.getEmail();
		String role = searchUserDto.getRole();

		// Constructing the query based on provided criteria
		Specification<User> spec = Specification.where(null);

		if (firstName != null && !firstName.isEmpty()) {
			spec = spec.and((root, query, builder) -> builder.like(root.get("firstName"), "%" + firstName + "%"));
		}

		if (lastName != null && !lastName.isEmpty()) {
			spec = spec.and((root, query, builder) -> builder.like(root.get("lastName"), "%" + lastName + "%"));
		}

		if (email != null && !email.isEmpty()) {
			spec = spec.and((root, query, builder) -> builder.equal(root.get("email"), email));
		}

		if (role != null && !role.isEmpty()) {
			spec = spec.and((root, query, builder) -> builder.equal(root.get("role"), "%" + role + "%"));
		}

		// Fetching users based on the constructed query
		return repository.findAll(spec);
	}

	public void create(User user) {
		repository.save(user);
	}

	// UPDATE
	public void update(User dbUser) {
		repository.save(dbUser);
	}

	// DELETE
	public void delete(long id) {
		repository.deleteById(id);
	}

}
