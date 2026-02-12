package io.github.davidmart7n.bookingslots.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.davidmart7n.bookingslots.domain.Appointment;
import io.github.davidmart7n.bookingslots.service.BookingService;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/booking")
public class BookingController {
    
    private final BookingService service;


    public BookingController(BookingService service){
        this.service=service;
    }

    // @GetMapping()
    // public ResponseEntity<List<Appointment>> findAll(){
    //     return ResponseEntity.ok(this.service.findAll());
    // }

    @GetMapping()
    public ResponseEntity<List<Appointment>> findByDate(@RequestParam("date")
                        @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate date
    ){
        return ResponseEntity.ok(this.service.findByDate(date));
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody @Valid Appointment appointment){
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(this.service.addAppointment(appointment));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        this.service.removeAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
