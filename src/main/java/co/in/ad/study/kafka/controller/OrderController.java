package co.in.ad.study.kafka.controller;

import co.in.ad.study.kafka.assemble.OrderDetailsAssembler;
import co.in.ad.study.kafka.domain.OrderDetailsDo;
import co.in.ad.study.kafka.dto.OrderDetailsDto;
import co.in.ad.study.kafka.repository.OrderDetailsRepository;
import co.in.ad.study.kafka.service.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/study/event/publish/order")
public class OrderController {

    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    private OrderDetailsAssembler orderDetailsAssembler;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @RequestMapping(value = "/", produces = {"application/json"},consumes = {"application/json"}, method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity<OrderDetailsDto> publishOrderEvent(@RequestBody OrderDetailsDto orderDetailsDto) {
        try {

            OrderDetailsDo orderDetailsDo = orderDetailsAssembler.assembleOrderDetailsDomain(orderDetailsDto);

            orderDetailsRepository.save(orderDetailsDo);

            orderDetailsDto = orderDetailsAssembler.assembleOrderDetailsDto(orderDetailsDo);

            orderProducer.sendMessage(orderDetailsDto);

            return new ResponseEntity<OrderDetailsDto>(orderDetailsDto, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<OrderDetailsDto>(orderDetailsDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
