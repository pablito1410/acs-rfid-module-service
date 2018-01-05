package com.pablo.acs.rfid.service.infrastructure.rest;

import com.pablo.acs.rfid.service.domain.rfid.EnrollCommand;
import com.pablo.acs.rfid.service.domain.rfid.RfidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rfid-module")
public class RFIDModuleController {

    private final RfidService rfidService;

    @Autowired
    public RFIDModuleController(final RfidService rfidService) {
        this.rfidService = rfidService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/enroll", method = RequestMethod.POST)
    public ResponseEntity enroll(@RequestBody final EnrollCommand command) {
        rfidService.handle(command);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
