package co.in.ad.study.kafka.controller;

import co.in.ad.study.kafka.assemble.CustomerDetailsAssembler;
import co.in.ad.study.kafka.domain.CustomerDo;
import co.in.ad.study.kafka.dto.CustomerDto;
import co.in.ad.study.kafka.repository.CustomerRepository;
import co.in.ad.study.kafka.service.CustomerProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/study/event/publish/customer")
public class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerProducer customerProducer;

    @Autowired
    private CustomerDetailsAssembler assembleOrderDetailsDto;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/", produces = {"application/json"},consumes = {"application/json"}, method = RequestMethod.POST)
    public @ResponseBody  ResponseEntity<CustomerDto> publishOrderEvent(@RequestBody CustomerDto customerDto) {
        try {

            CustomerDo customerDo = assembleOrderDetailsDto.assembleCustomerDetailsDomain(customerDto);

            customerRepository.save(customerDo);
            customerDto = assembleOrderDetailsDto.assembleCustomerDetailsDto(customerDo);

            customerProducer.sendMessage(customerDto);

            return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.OK);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
