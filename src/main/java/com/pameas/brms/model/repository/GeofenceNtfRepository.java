package com.pameas.brms.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.pameas.brms.model.entity.GeofenceNtf;

@Repository
public interface GeofenceNtfRepository extends MongoRepository<GeofenceNtf, String> {

}
