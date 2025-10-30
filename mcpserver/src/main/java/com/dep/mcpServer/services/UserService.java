package com.dep.mcpServer.services;
import com.dep.mcpServer.repositories.UserRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Tool(
			name = "userExists",
			description = "Checks if a user with the given first and last name exists in the system. Returns true if the user exists, false otherwise."
	)
	public boolean userExists(String firstName, String lastName) {
		return userRepository.findUserByFirstNameAndLastName(firstName, lastName) != null;
	}
}
