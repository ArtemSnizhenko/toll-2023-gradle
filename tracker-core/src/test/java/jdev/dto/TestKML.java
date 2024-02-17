package jdev.dto;

import jdev.tracker.KMLTrek;

import org.junit.Test;

/**
 * Класс тестирования получения координат из KML файла
 * Created by artem on 04.02.24.
 */

public class TestKML {

    KMLTrek kmlTest = new KMLTrek();

    @Test
    public void testKmlDo()throws Exception {
        double[] tempArr = kmlTest.getCoordinates();
        for (int k=0;k < 1000;k++){
            System.out.println(tempArr[0]+" / "+
                    tempArr[1]+" / "+
                    tempArr[2]);
        }
    }
}
