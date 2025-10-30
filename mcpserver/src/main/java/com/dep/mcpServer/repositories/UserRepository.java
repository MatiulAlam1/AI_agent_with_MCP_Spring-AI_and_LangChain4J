package com.dep.mcpServer.repositories;
import com.dep.mcpServer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findUserByFirstNameAndLastName(String firstName, String lastName);
}
