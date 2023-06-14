package org.rosatom.vcharugin.repositories;

import org.rosatom.vcharugin.models.Model;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends MongoRepository<Model, String> {
}
