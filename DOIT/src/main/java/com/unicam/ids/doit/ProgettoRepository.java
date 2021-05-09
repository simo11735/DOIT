package com.unicam.ids.doit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgettoRepository extends CrudRepository<Progetto, Integer> {
}
