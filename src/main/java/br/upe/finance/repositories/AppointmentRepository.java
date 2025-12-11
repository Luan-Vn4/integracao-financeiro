package br.upe.finance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upe.finance.models.Appointment;

@Repository
public interface AppointmentRepository
    extends JpaRepository<Appointment, Integer> {}