package com.pameas.brms.model.repository;

import com.pameas.brms.model.entity.GeofenceNtf;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeofenceNtfRepository extends MongoRepository<GeofenceNtf, String> {

}
