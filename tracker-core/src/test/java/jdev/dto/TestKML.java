package jdev.dto;

import jdev.tracker.KMLreadTrek;

import org.junit.Test;

/**
 * Created by artem on 04.02.24.
 */

public class TestKML {

    KMLreadTrek kmlTest = new KMLreadTrek();

    @Test
    public void testKmlDo()throws Exception {

        for (int k=0;k < 1000;k++){
            double[] tempArr = kmlTest.getCoordinates();
            System.out.println(tempArr[0]+" / "+
                    tempArr[1]+" / "+
                    tempArr[2]);
        }
    }
}
