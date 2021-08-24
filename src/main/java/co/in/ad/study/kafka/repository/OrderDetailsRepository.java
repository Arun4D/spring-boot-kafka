package co.in.ad.study.kafka.repository;

import co.in.ad.study.kafka.domain.OrderDetailsDo;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailsRepository extends CrudRepository<OrderDetailsDo, Long> {
}
