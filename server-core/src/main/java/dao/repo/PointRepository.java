package dao.repo;

import dao.TrackPoint;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface PointRepository extends CrudRepository<TrackPoint, Integer> {
    List<TrackPoint> findByTrackerId(int trackerId, Pageable page);
}
