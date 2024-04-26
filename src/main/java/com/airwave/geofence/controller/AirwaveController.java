package com.airwave.geofence.controller;

import com.airwave.geofence.model.Geofence;
import com.airwave.geofence.repository.GeofenceRepository;
import com.airwave.geofence.service.GeofenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/airwave")
public class AirwaveController {

    @Autowired
    private GeofenceService geofenceService;

    StringBuilder sizeString = new StringBuilder("Size:");

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }


    @GetMapping(value = "/geofence", produces = "application/json")
    public Map<String,List<Geofence>> getAllGeofence(){

        List<Geofence> geofenceList =geofenceService.getAllGeofence();
        int Size = geofenceService.geoFenceSize();
        sizeString.append(Size);
        String size = sizeString.toString();

        return Map.of(size,geofenceList);

    }

    @GetMapping(value = "/geofence/{id}", produces = "application/json")
    public Geofence getGeofence(@PathVariable int id){
        return geofenceService.getGeofenceById(id);

    }

    @GetMapping(value = "/geofence/{viewName}", produces = "application/json")
    public Geofence getGeofenceByViewName(@PathVariable String viewName){
        return geofenceService.getGeofenceByViewName(viewName);

    }





}


