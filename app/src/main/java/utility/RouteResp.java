package utility;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import models.Position;

/**
 * Created by William on 10/08/17.
 * Classe java para a
 */

public class RouteResp {
    private List<Position> rota;
    public List<Position> getRota() {
        return rota;
    }

    public void setRota(List<Position> rota) {
        this.rota = rota;
    }
}
