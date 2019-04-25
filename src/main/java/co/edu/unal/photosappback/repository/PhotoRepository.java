package co.edu.unal.photosappback.repository;

import co.edu.unal.photosappback.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer>, JpaSpecificationExecutor<Photo> {

}
