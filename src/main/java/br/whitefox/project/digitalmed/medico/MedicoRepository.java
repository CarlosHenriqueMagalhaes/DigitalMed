package br.whitefox.project.digitalmed.medico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository <Medico, Long> {
}
