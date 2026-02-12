package io.github.davidmart7n.bookingslots.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.davidmart7n.bookingslots.domain.Appointment;

@Repository
public interface BookingRepository extends JpaRepository<Appointment,Long>{


    public List<Appointment> findByDate(LocalDate date);
}