package co.in.ad.study.kafka.repository;

import co.in.ad.study.kafka.domain.CustomerDo;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerDo, Long> {
}
