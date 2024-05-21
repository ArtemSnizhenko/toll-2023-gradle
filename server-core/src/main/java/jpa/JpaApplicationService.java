package jpa;

import dao.TrackPoint;
import dao.repo.PointRepository;
import dao.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableJpaRepositories("dao")
@EntityScan(basePackageClasses = TrackPoint.class)
@Service
public class JpaApplicationService {

    private static final Logger log = LoggerFactory.
            getLogger(JpaApplicationService.class);

    private List<TrackPoint> all;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Autowired
    PointRepository pointRepository;

    @Autowired
    UserRepository userRepository;

    /*извлечение из базы нужного количества
    объектов типа TrackPoint*/
    public  Iterable<TrackPoint> take(int trackerId, int size) throws InterruptedException {

        /*Извелечение заданного числа отметок, с заданым идентификатором
         отсортированным по убыванию свойства "id" (самых поздних) */
        PageRequest page = new PageRequest(0,size, Sort.Direction.DESC,"id");
        return pointRepository.findByTrackerId(trackerId, page);
    }

    public Iterable<TrackPoint> readTable() throws InterruptedException {
        return pointRepository.findAll();
    }

    /*размещение данных в БД*/
    public void put(TrackPoint trackPoint){
        pointRepository.save(trackPoint);
    }

    /*удаление данных в БД*/
    private void delete(TrackPoint trackPoint) {
        pointRepository.delete(trackPoint);
    }


}
