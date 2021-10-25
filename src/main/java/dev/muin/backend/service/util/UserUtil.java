package dev.muin.backend.service.util;

import dev.muin.backend.domain.Store.Location;
import dev.muin.backend.web.request.AddManagerRoleRequest;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    private final static double ERROR_RANGE = Double.valueOf(0.001);

    public static boolean isLocationValid(AddManagerRoleRequest addManagerRoleRequest, Location location){
        double reqLat = addManagerRoleRequest.getStoreLat();
        double reqLon = addManagerRoleRequest.getStoreLon();

        if(calcErrorRange(reqLat, location.getLatitude())
                && calcErrorRange(reqLon, location.getLongitude())){
            return true;
        }
        return false;
    }

    private static boolean calcErrorRange(double d1, double d2) {
        return Double.compare(Math.abs(d1-d2), ERROR_RANGE)<=0;
    }
}
