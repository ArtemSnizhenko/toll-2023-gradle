package service;

import dao.TrackPoint;
import dao.User;
import dao.repo.PointRepository;
import dao.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@EnableJpaRepositories("dao")
@EntityScan(basePackageClasses = TrackPoint.class)
@Service
public class JpaInteractionService {

    private static final Logger log = LoggerFactory.
            getLogger(JpaInteractionService.class);

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
    public  Iterable<TrackPoint> take(int trackerId, int size)
            throws InterruptedException {

        /*Извлечение заданного числа отметок, с заданным идентификатором
        отсортированным по убыванию свойства "id" (самых поздних) */
        PageRequest page = new PageRequest(0, size, Sort.Direction.DESC,"id");
        return pointRepository.findByTrackerId(trackerId, page);
    }

    /*получение данных из таблицы TrackPoint*/
    public Iterable<TrackPoint> readTableTrackPoint() throws InterruptedException {
        return pointRepository.findAll();
    }

    /*получение данных из таблицы User*/
    public Iterable<User> readTableUser() throws InterruptedException {
        return userRepository.findAll();
    }

    /*получение данных из таблицы User*/
    public User getRowUserId(int id) throws InterruptedException {
        return userRepository.findById(id);
    }

    /*размещение данных в БД*/
    public void put(TrackPoint trackPoint){
        pointRepository.save(trackPoint);
    }

    /*удаление данных в таблице TrackPoint*/
    public void delete(TrackPoint trackPoint) {
        pointRepository.delete(trackPoint);
    }

    /*удаление данных в таблице User
    * перегрузка метода delet*/
    public void delete(User user) {
        userRepository.delete(user);
    }

    /*обновление данных в таблице TrackPoint*/
    public void update(TrackPoint trackPoint,
                        int trackerId,
                        double latitude,
                        double longitude,
                        double azimuth,
                        double speed) {
        trackPoint.setTrackerId(trackerId);
        trackPoint.setLatitude(latitude);
        trackPoint.setLongitude(longitude);
        trackPoint.setAzimuth(azimuth);
        trackPoint.setSpeed(speed);
        pointRepository.save(trackPoint);
    }

    /*обновление данных в таблице User
    * перегрузка метода update*/
    public void update(User user,
                        String userName,
                        String password,
                        String roles) {
        user.setUserName(userName);
        user.setPassword(password);
        user.setRoles(roles);
        userRepository.save(user);
    }
}
