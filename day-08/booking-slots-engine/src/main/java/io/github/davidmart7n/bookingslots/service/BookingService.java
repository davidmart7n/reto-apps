package io.github.davidmart7n.bookingslots.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.davidmart7n.bookingslots.domain.Appointment;
import io.github.davidmart7n.bookingslots.repository.BookingRepository;

@Service
public class BookingService {

    private BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> findAll() {

        return this.repository.findAll();

    }

    public List<Appointment> findByDate(LocalDate date) {
        return this.repository.findByDate(date);
    }

    public Appointment addAppointment(Appointment appointment) {
 
            if (this.repository.findByDate(appointment.getDate())
                    .stream()
                    .anyMatch(ap -> ap.getStartHour() == appointment.getStartHour())) {
                throw new RuntimeException("This time is occupated");
            }

                
        return this.repository.save(appointment);
    }

public void removeAppointment(Long id){
        if (!this.repository.existsById(id)) {
            throw new RuntimeException("Appointment with ID" + id + " doesn't exist");
        }
        this.repository.deleteById(id);
    }
}
