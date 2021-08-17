package co.in.ad.study.kafka.controller;

import co.in.ad.study.kafka.assemble.OrderDetailsAssembler;
import co.in.ad.study.kafka.core.model.EventEntity;
import co.in.ad.study.kafka.domain.OrderDetailsDomain;
import co.in.ad.study.kafka.dto.OrderDetailsDto;
import co.in.ad.study.kafka.repository.OrderDetailsRepository;
import co.in.ad.study.kafka.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/study/event/publish")
public class OrderController {

    @Autowired
    private Producer producer;

    @Autowired
    private OrderDetailsAssembler orderDetailsAssembler;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @RequestMapping(value = "/", produces = {"application/json"},consumes = {"application/json"}, method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity<EventEntity> publishOrderEvent(@RequestBody EventEntity eventEntity) {
        try {
            OrderDetailsDto orderDetailsDto = orderDetailsAssembler.assembleOrderDetailsDto(eventEntity);

            OrderDetailsDomain orderDetailsDomain = orderDetailsAssembler.assembleOrderDetailsDomain(orderDetailsDto);

            orderDetailsRepository.save(orderDetailsDomain);

            eventEntity.setData(orderDetailsDomain);

            producer.sendMessage(eventEntity);

            return new ResponseEntity<EventEntity>(eventEntity, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<EventEntity>(eventEntity, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
