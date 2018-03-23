package utility;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

/**
 * Created by William on 31/01/17.
 * Classe java para a
 */
public class GPSTracker extends Service implements LocationListener {

	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60;
	private final Context context;
	private boolean canGetLocation = false;
	private LocationManager locationManager;
	private boolean isGPSEnabled = false;
	private boolean isNetworkEnabled = false;
	private Location location;
	private Double latitude;
	private Double longitude;
	private Float accuracy;

	public GPSTracker(Context context) {
		this.context = context;
		getLocation();
	}

	public Location getLocation() {
		try {
			locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

			isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!isGPSEnabled && !isNetworkEnabled) {

			} else {
				this.canGetLocation = true;

				if (isNetworkEnabled) {

					locationManager.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER,
							MIN_TIME_BW_UPDATES,
							MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

					if (locationManager != null) {
						location = locationManager
								.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

						if (location != null) {

							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
					}

				}

				if (isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						if (locationManager != null) {
							location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

							if (location != null) {
								latitude = location.getLatitude();
								longitude = location.getLongitude();
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return location;
	}


	public Double getLatitude() {
		if (location != null) {
			latitude = location.getLatitude();
		}
		return latitude;
	}

	public Double getLongitude() {
		if (location != null) {
			longitude = location.getLongitude();
		}

		return longitude;
	}

	public Float getAccuracy() {
		if (accuracy.isNaN()) {
			accuracy = location.getAccuracy();
		}

		return accuracy;
	}

	public boolean canGetLocation() {
		return this.canGetLocation;
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}