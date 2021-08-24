package co.in.ad.study.kafka.assemble;

import co.in.ad.study.kafka.domain.OrderDetailsDo;
import co.in.ad.study.kafka.dto.OrderDetailsDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailsAssembler {

    public OrderDetailsDo assembleOrderDetailsDomain(OrderDetailsDto orderDetailsDto) {
        ModelMapper modelMapper = new ModelMapper();
        OrderDetailsDo orderDetailsDo = modelMapper.map(orderDetailsDto, OrderDetailsDo.class);
        return orderDetailsDo;
    }

    public OrderDetailsDto assembleOrderDetailsDto(OrderDetailsDo orderDetailsDo) {
        ModelMapper modelMapper = new ModelMapper();
        OrderDetailsDto orderDetailsDto = modelMapper.map(orderDetailsDo, OrderDetailsDto.class);
        return orderDetailsDto;
    }
}
