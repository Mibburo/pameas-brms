package com.pameas.brms.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.pameas.brms.model.entity.ClientLocation;

@Repository
public interface ClientLocationRepository extends MongoRepository<ClientLocation, String> {

    ClientLocation findByMacAddress(String macAddress);
}
