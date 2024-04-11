package nl.workingtalent.wtacademy.user;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	// List user -> stream ReadUserDto
	public Stream<ReadUserDto> convertUsersToReadUserDtos(List<User> users) {
		return users.stream().map(user -> {
			return new ReadUserDto(user);
		});
	}
	
	// Hier kan je meerdere mappings neerzetten

}
