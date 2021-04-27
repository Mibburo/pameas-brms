package com.pameas.brms.model.repository;

import com.pameas.brms.model.entity.ClientGeofence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientGeofenceRepository extends MongoRepository<ClientGeofence, String> {

    ClientGeofence findByMacAddress(String macAddress);
}
