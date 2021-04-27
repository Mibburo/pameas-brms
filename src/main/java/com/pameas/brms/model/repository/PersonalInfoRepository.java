package com.pameas.brms.model.repository;

import com.pameas.brms.model.entity.PersonalInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends MongoRepository<PersonalInfo, String> {

    @Query(value = "{ 'macAddress' :  ?0 }", fields = "{ '_id': 0, 'clientAddress':1 }")
    PersonalInfo findClientAddressByMacAddress(String macAddress);
}
