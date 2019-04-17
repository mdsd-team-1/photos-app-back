package co.edu.unal.photosappback.repository;

import co.edu.unal.photosappback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends JpaRepository<User, Integer> {

}
