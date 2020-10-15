package ben.mur.bluefill.database.repositories;

import ben.mur.bluefill.database.entities.QuotesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface QuotesRepository extends CrudRepository<QuotesEntity, Integer> {
    ArrayList<QuotesEntity> findAll();
    ArrayList<QuotesEntity> findAllByMovies_DirectorsId(Integer id);
}
