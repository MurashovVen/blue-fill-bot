package ben.mur.bluefill.database.repositories;

import ben.mur.bluefill.database.entities.DirectorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DirectorsRepository extends JpaRepository<DirectorsEntity, Integer> {
    ArrayList<DirectorsEntity> findAll();
}
