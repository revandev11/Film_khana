package org.my.project.repository;

import org.my.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Userrepository extends JpaRepository<User,Long> {
}
