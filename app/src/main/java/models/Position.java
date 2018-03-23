package models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by William on 10/08/17.
 * Classe java para a
 */

public class Position {
    private String latitude;
    private String longitude;
    private String endereco;
    private String utcDateTime;

    public String getUtcDateTime() {
        return utcDateTime;
    }

    public void setUtcDateTime(String utcDateTime) {
        this.utcDateTime = utcDateTime;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
