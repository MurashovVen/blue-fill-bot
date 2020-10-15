package ben.mur.bluefill.database.repositories;

import ben.mur.bluefill.database.entities.MoviesEntity;
import org.springframework.data.repository.CrudRepository;

public interface MoviesRepository extends CrudRepository<MoviesEntity, String> {
}
