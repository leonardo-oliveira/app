package br.com.technolog.checklist;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import models.Position;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utility.JornadaService;
import utility.RouteResp;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, Callback<RouteResp> {

    private GoogleMap mMap;
    private String placa;
    private List<Position> rota;
    private Integer cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        placa = getIntent().getExtras().get("placa").toString();
        cod = getIntent().getExtras().getInt("cod");
        //Obtain rota
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        getRoute();
    }

    private void getRoute() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JornadaService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JornadaService service = retrofit.create(JornadaService.class);
        Call<RouteResp> requestRoute = service.rota(cod, placa);
        requestRoute.enqueue(this);
    }

    /**
     * Invoked for a received HTTP response.
     * <p>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link Response#isSuccessful()} to determine if the response indicates success.
     *
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<RouteResp> call, Response<RouteResp> response) {
        if (response.isSuccessful()) {
            rota = response.body().getRota();
            Float lat = null;
            Float longi = null;
            int i = 0;
            int size = rota.size() - 1;
            Marker marker = null;
            PolylineOptions lineOptions = new PolylineOptions();;
            ArrayList<LatLng> points = new ArrayList<>();
            for (Position a : rota) {
                if (i % 10 == 0) {
                    lat = Float.valueOf(a.getLatitude());
                    longi = Float.valueOf(a.getLongitude());
                    LatLng posi = new LatLng(lat, longi);
                    points.add(posi);
                    /**/
                    if (i == 0) {
                        MarkerOptions markerOptions = new MarkerOptions()
                                .position(posi)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                        marker = mMap.addMarker(markerOptions);
                        marker.setSnippet(a.getEndereco());
                        marker.setTitle("Ponto Atual");
                        marker.showInfoWindow();

                    }
                }
                i++;
            }
            lineOptions.addAll(points);
            lineOptions.width(5);
            lineOptions.color(Color.RED);
            mMap.addPolyline(lineOptions);
            LatLng posi = new LatLng(lat, longi);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(posi));
            mMap.animateCamera( CameraUpdateFactory.zoomTo( 5.0f ) );
        }
    }

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<RouteResp> call, Throwable t) {
        Log.e("kkk", "onFailure: " + "deu ruim");
        t.getMessage();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
