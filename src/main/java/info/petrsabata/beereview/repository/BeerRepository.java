package info.petrsabata.beereview.repository;

import info.petrsabata.beereview.model.Beer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends MongoRepository<Beer, Integer> {

}
