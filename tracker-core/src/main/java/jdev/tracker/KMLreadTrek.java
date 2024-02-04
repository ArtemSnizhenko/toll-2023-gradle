package jdev.tracker;

import de.micromata.opengis.kml.v_2_2_0.*;

import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * Created by artem on 03.02.24.
 */
public class KMLreadTrek {
    private URL url= KMLreadTrek.class.getResource("/KML_Exempl.kml");
    private Kml kml = Kml.unmarshal(new File(url.getPath()));
    private Document document = (Document)kml.getFeature();
    private List<Feature> folders = document.getFeature();
    private Folder folder = (Folder) folders.get(1);
    private List<Feature> placemarks = folder.getFeature();
    private Placemark placemark =  (Placemark) placemarks.get(0);
    private LineString geometry = (LineString) placemark.getGeometry();
    private List<Coordinate> coordinates = geometry.getCoordinates();
    private int i = 0;

    public double[] getCoordinates() {
        int sizeTrek = coordinates.size();
        if (i > sizeTrek - 1) {
            i = 0;
        }
        double[] arrTmp = {coordinates.get(i).getLongitude(),coordinates.get(i).getLatitude(),coordinates.get(i).getAltitude()};
        i++;
        return arrTmp;
    }

}
