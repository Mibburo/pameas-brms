package com.pameas.brms.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.pameas.brms.model.entity.ClientGeofence;

@Repository
public interface ClientGeofenceRepository extends MongoRepository<ClientGeofence, String> {

    ClientGeofence findByMacAddress(String macAddress);
}
