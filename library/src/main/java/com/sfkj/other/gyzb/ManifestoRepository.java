package com.sfkj.other.gyzb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ManifestoRepository extends MongoRepository<Manifesto, String> {

}