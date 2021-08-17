package co.in.ad.study.kafka.repository;

import co.in.ad.study.kafka.domain.OrderDetailsDomain;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailsRepository extends CrudRepository<OrderDetailsDomain, Long> {
}
