package com.wf.dataabstraction.repository;

import com.wf.dataabstraction.entity.CityDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDetailsRepository extends CrudRepository<CityDetails, String> {
    boolean existsByCityName(String cityName);
}
