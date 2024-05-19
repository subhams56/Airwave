package com.airwave.geofence.service;

import com.airwave.geofence.model.Geofence;
import com.airwave.geofence.repository.GeofenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GeofenceService {


    private GeofenceRepository geofenceRepository;


    @Autowired
    public GeofenceService(GeofenceRepository geofenceRepository) {
        this.geofenceRepository = geofenceRepository;
    }


    public List<Geofence> getAllGeofence() {
        return geofenceRepository.findAll();
    }

    public int geoFenceSize() {
        return geofenceRepository.geoFenceSize();
    }

    public List<Geofence> getGeofenceById(int id) {
        return geofenceRepository.getGeofenceById(id);
    }

    public Geofence getGeofenceByViewName(String viewname) {
        return geofenceRepository.getGeofenceByViewName(viewname);
    }
}

