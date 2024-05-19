package com.airwave.geofence.controller;

import com.airwave.geofence.model.Geofence;
import com.airwave.geofence.service.GeofenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/airwave")
public class AirwaveController {


    private static final Logger logger = LoggerFactory.getLogger(AirwaveController.class);

    private GeofenceService geofenceService;

    @Autowired
    public AirwaveController(GeofenceService geofenceService) {
        this.geofenceService = geofenceService;
    }


    @RequestMapping("/healthCheck")
    public String healthCheck(){
        return " Geofence API Service is up and running";
    }



    @GetMapping(value = "/geofence", produces = "application/json")
    public ResponseEntity<Map<String,List<Geofence>>> getAllGeofence(){
        try {
            List<Geofence> geofenceList = geofenceService.getAllGeofence();
            int Size = geofenceService.geoFenceSize();
            String SizeString = "Size : " + Size;
            logger.info(SizeString);
            ResponseEntity<Map<String,List<Geofence>>> responseEntity = new ResponseEntity<>(Collections.singletonMap(SizeString,geofenceList), HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping(value = "/geofence/id/{id}", produces = "application/json")
    public List<Geofence> getGeofence(@PathVariable int id){
        return geofenceService.getGeofenceById(id);

    }

    @GetMapping(value = "/geofence/viewname/{viewname}", produces = "application/json")
    public Geofence getGeofenceByViewName(@PathVariable String viewname){
        return geofenceService.getGeofenceByViewName(viewname);

    }





}


