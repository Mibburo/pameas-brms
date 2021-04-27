package com.pameas.brms.model.repository;

import com.pameas.brms.model.entity.ClientLocation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientLocationRepository extends MongoRepository<ClientLocation, String> {

    ClientLocation findByMacAddress(String macAddress);
}
