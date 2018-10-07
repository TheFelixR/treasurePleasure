package treasure.pleasure.model;

import android.graphics.Color;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import java.util.ArrayList;
import treasure.pleasure.R;

class GameMap {

  //Hardcoded locations
  private final LatLng KLATTERLABBET = new LatLng(57.6874681, 11.9782412);
  private final LatLng DELTAPARKEN = new LatLng(57.6875713, 11.9795823);
  // GameMap locations
  private final LatLng
      MAPCENTER = new LatLng(57.688067, 11.977898),
      mapLimitNW = new LatLng(57.863889, 11.410027),
      mapLimitNE = new LatLng(57.848447, 12.387770),
      mapLimitSW = new LatLng(57.563985, 12.193909),
      mapLimitSE = new LatLng(57.554888, 11.627327),
      mapNW = new LatLng(57.690700, 11.970995),
      mapNE = new LatLng(57.690708, 11.976745),
      mapSW = new LatLng(57.685990, 11.982750),
      mapSE = new LatLng(57.685446, 11.977415);
  private final ArrayList<LatLng> MAPBOUNDARY = new ArrayList<LatLng>() {{
    add(mapNW);
    add(mapNE);
    add(mapSW);
    add(mapSE);
    add(mapNW); // to "close" box
  }};
  private GoogleMap mMap;
  //Markers
  private Marker treasureChest;
  private Marker gemOne;

  // number of items on map
  private int numberOfItems;

  GameMap() {
    // Add markers and build play map
    createPolygonMap();

    // position camera
    mMap.setMinZoomPreference(15.0f);
    mMap.moveCamera(CameraUpdateFactory.newLatLng(MAPCENTER));
    numberOfItems = 0;

  }

  private void createPolygonMap() {
    mMap.addPolygon(new PolygonOptions()
        .add(mapLimitNW, mapLimitNE, mapLimitSW, mapLimitSE)
        .addHole(MAPBOUNDARY)
        .strokeColor(Color.BLACK)
        .fillColor(Color.argb(220, 0, 0, 0)));

  }

  void addMarker(LatLng latLng) {
    mMap.addMarker(new MarkerOptions()
        .position(latLng)
        .title("hej")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.gem_tiny))
    );
    numberOfItems++;
  }

  // getters
  GoogleMap getmMap() {
    return this.mMap;
  }
  int getNumberOfItems() {return numberOfItems; }


}

/* VIEW/PRESENTER SPECIFIC
    mMap.setOnMarkerClickListener(this);
    MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style);
    mMap.setMapStyle(style);
* */