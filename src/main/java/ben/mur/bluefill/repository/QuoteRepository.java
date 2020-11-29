package ben.mur.bluefill.repository;

import ben.mur.bluefill.model.entity.Quote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Integer> {
    ArrayList<Quote> findAll();
    ArrayList<Quote> findAllByMovies_DirectorsId(Integer id);
}
