package com.pluralsight.conference.respository;

import com.pluralsight.conference.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
