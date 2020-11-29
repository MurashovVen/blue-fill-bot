package ben.mur.bluefill.repository;

import ben.mur.bluefill.model.entity.Director;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DirectorRepository extends CrudRepository<Director, Integer> {
    ArrayList<Director> findAll();
}
