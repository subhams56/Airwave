package com.airwave.geofence.service;

import com.airwave.geofence.model.Geofence;
import com.airwave.geofence.repository.GeofenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GeofenceService {

    @Autowired
    private GeofenceRepository geofenceRepository;


    public List<Geofence> getAllGeofence() {
        return geofenceRepository.FindAll();
    }

    public int geoFenceSize() {
        return geofenceRepository.geoFenceSize();
    }

    public Geofence getGeofenceById(int id) {
        return geofenceRepository.getGeofenceById(id);
    }

    public Geofence getGeofenceByViewName(String viewName) {
        return geofenceRepository.getGeofenceByViewName(viewName);
    }
}

