package co.in.ad.study.kafka.assemble;

import co.in.ad.study.kafka.core.model.EventEntity;
import co.in.ad.study.kafka.domain.OrderDetailsDomain;
import co.in.ad.study.kafka.dto.OrderDetailsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailsAssembler {

    public OrderDetailsDomain assembleOrderDetailsDomain(OrderDetailsDto orderDetailsDto) {
        OrderDetailsDomain orderDetailsDomain = new OrderDetailsDomain();
        orderDetailsDomain.setItemId(Long.valueOf(orderDetailsDto.getItemId()));
        orderDetailsDomain.setCustomerId(Long.valueOf(orderDetailsDto.getCustomerId()));
        return orderDetailsDomain;
    }

    public OrderDetailsDto assembleOrderDetailsDto(EventEntity eventEntity) {
        ObjectMapper om = new ObjectMapper();
        OrderDetailsDto orderDetailsDto = null;
        try {
            String s = om.writeValueAsString(eventEntity.getData());
            orderDetailsDto = om.readValue(s, OrderDetailsDto.class);

        }catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return orderDetailsDto;
    }
}
