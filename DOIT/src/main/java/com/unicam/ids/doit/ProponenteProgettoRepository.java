package com.unicam.ids.doit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProponenteProgettoRepository extends CrudRepository<ProponenteProgetto, Integer> {
}
