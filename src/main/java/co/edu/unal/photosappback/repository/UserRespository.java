package co.edu.unal.photosappback.repository;

import co.edu.unal.photosappback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRespository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    User findByEmailAndPassword(String email, String password);

}