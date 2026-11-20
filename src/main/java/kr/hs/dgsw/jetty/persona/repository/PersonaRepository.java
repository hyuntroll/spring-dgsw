package kr.hs.dgsw.jetty.persona.repository;

import kr.hs.dgsw.jetty.persona.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findAllByOrderByPersonaIdAsc();
}
