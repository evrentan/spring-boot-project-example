package evrentan.examples.springbootprojectexample.repository;

import evrentan.examples.springbootprojectexample.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {
}
