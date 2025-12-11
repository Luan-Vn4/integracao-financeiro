package br.upe.finance.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upe.finance.models.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {}