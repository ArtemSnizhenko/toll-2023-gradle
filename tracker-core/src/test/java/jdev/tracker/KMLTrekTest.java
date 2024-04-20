package jdev.tracker;

import org.junit.Test;


import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by artem on 16.03.24.
 */
public class KMLTrekTest {
    @Test
    public void getCoordinates() throws Exception {
        KMLTrek kmlTest = new KMLTrek();
        assertNotNull(kmlTest.getCoordinates());
        assertEquals(3,kmlTest.getCoordinates().length);
    }
}