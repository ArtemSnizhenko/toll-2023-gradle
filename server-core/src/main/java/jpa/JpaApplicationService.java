package jpa;

import dao.TrackPoint;
import dao.repo.PointRepository;
import jdev.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableJpaRepositories("dao")
@EntityScan(basePackageClasses = TrackPoint.class)
@Service
public class JpaApplicationService implements CommandLineRunner { //имплементируем интерфейс CommandLineRunner (командная строка запуска)

    private static final Logger log = LoggerFactory.
            getLogger(JpaApplicationService.class);

    private List<TrackPoint> all;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Autowired
    PointRepository pointRepository;

    /*извлечение из базы объектов типа TrackPoint*/
    public TrackPoint take() throws InterruptedException {
        return read();
    }

    public void put(TrackPoint trackPoint){
        pointRepository.save(trackPoint);
    }

    @Override
//переопределяем метод который позволит
//нам выполнять методы нашего приложения при запуске
    public void run(String... args) throws Exception {
        /*Point point = new TrackPoint();
        point.setLatitude(56);
        point.setLongitude(34);
        point.setAzimuth(44);
        point.setSpeed(90);

        TrackPoint trackPoint = create(point);
//        read();

//        log.info("=======after create");
        TrackPoint t = take();
        log.info(t.toJson());*/
    }

    private void delete(TrackPoint trackPoint) {
        pointRepository.delete(trackPoint);
    }


    private TrackPoint read() {
        all = (List<TrackPoint>) pointRepository.findAll();

        if (all.size() == 0) {
            log.info("NO RECORDS");
        } else {
            all.stream().forEach(trackPoint -> log.info(trackPoint.toString()));
        }
        return all.get(0);
    }

    private TrackPoint create(Point point) {
//        trackPoint.setModel(model);
        return pointRepository.save((TrackPoint)point);
    }
}
