package co.in.ad.study.kafka.assemble;

import co.in.ad.study.kafka.domain.CustomerDo;
import co.in.ad.study.kafka.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerDetailsAssembler {

    public CustomerDo assembleCustomerDetailsDomain(CustomerDto customerDto) {

        ModelMapper modelMapper = new ModelMapper();
        CustomerDo customerDo = modelMapper.map(customerDto, CustomerDo.class);
        return customerDo;
    }
    public CustomerDto assembleCustomerDetailsDto(CustomerDo customerDo) {

        ModelMapper modelMapper = new ModelMapper();
        CustomerDto customerDto = modelMapper.map(customerDo, CustomerDto.class);
        return customerDto;
    }

}
