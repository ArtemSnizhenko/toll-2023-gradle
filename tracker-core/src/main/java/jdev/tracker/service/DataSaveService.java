package jdev.tracker.service;

import jdev.dto.Point;
import org.springframework.stereotype.Service;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by artem on 20.01.24.
 * Сервис хранения сообщений
 */

@Service
public class DataSaveService {

    /*
     *определяем блокирующую, двунаправленную очередь, на обьект типа Point,
     * емкостью 100 элементов
     */
    private BlockingDeque<Point> queue =  new LinkedBlockingDeque<>(100);
    private int putCount;
    int i;

    /*заполнение очереди объектами типа Point*/
    void put(Point point) throws InterruptedException {
        i = putCount++;
        queue.put(point);
    }

    /*извлечение из очереди объектов типа Point*/
    Point take() throws InterruptedException {
        return queue.take();
    }
}