package br.com.walkito.fichaOnline.model.repositorys;

import br.com.walkito.fichaOnline.model.entities.FunFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FunFactRepository extends JpaRepository<FunFact, Integer> {

    @Query(value = "SELECT * FROM fun_facts f ORDER BY id DESC LIMIT 1", nativeQuery = true)
    public Optional<FunFact> findLastId();
}
