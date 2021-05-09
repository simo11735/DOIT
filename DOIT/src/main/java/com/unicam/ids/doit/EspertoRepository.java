package com.unicam.ids.doit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspertoRepository extends CrudRepository<Esperto, Integer> {
}
