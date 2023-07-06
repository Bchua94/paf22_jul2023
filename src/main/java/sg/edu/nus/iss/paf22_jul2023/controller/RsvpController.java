package sg.edu.nus.iss.paf22_jul2023.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.paf22_jul2023.model.RSVP;
import sg.edu.nus.iss.paf22_jul2023.repo.RsvpRepo;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/rsvps")
public class RsvpController {

    @Autowired
    RsvpRepo rsvpRepo;

    @GetMapping
    public ResponseEntity<List<RSVP>> getAll() {
        List<RSVP> rsvps = new ArrayList<>();
        rsvps = rsvpRepo.findAll();

        if (rsvps.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<RSVP>>(rsvps, HttpStatus.OK);
    }

    @GetMapping("/{rsvp-id}")
    public ResponseEntity<RSVP> getById(@PathVariable("rsvp-id") int id) {
        RSVP rsvp = rsvpRepo.findById(id);

        if (rsvp != null) {
            // return ResponseEntity.ofNullable(rsvp);
            return new ResponseEntity<RSVP>(rsvp, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/count")
    public ResponseEntity<Integer> getCount() {
        int count = rsvpRepo.count();

        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> saveRSVP(@RequestBody RSVP rsvp) {
        Boolean saved = false;

        saved = rsvpRepo.save(rsvp);

        if (saved) {
            return ResponseEntity.ok().body(saved);
        }

        return new ResponseEntity<>(saved, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{rsvp-id}")
    public ResponseEntity<Boolean> updateRSVP(@PathVariable("rsvp-id") int id, @RequestBody RSVP rsvp) {
        RSVP r = rsvpRepo.findById(id);

        Boolean bUpdated = false;
        if (r != null) {
            bUpdated = rsvpRepo.update(rsvp);
        }

        if (bUpdated) {
            return ResponseEntity.ok().body(bUpdated);
        }

        return ResponseEntity.ofNullable(bUpdated);
    }

}
