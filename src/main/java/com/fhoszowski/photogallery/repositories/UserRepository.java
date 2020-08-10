package com.fhoszowski.photogallery.repositories;

import com.fhoszowski.photogallery.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByLogin( String login );
}
