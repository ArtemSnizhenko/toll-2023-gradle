package dao.repo;

import dao.TrackPoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface PointRepository extends CrudRepository<TrackPoint, Integer> {
}
