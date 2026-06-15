package com.bank.core.resource;

import com.bank.core.dto.CustomerDto;
import com.bank.core.dto.ResponseDto;
import com.bank.core.entity.Customer;
import com.bank.core.mapper.CustomerMapper;
import com.bank.core.service.CustomerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api/bank/core/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerService customerService;
    @Inject
    CustomerMapper customerMapper;

    @POST
    public ResponseDto<CustomerDto> save(CustomerDto dto) {
        Customer savedCustomer = customerService.save(customerMapper.toEntity(dto));
        return new ResponseDto<>(
                customerMapper.toDto(savedCustomer)
        );
    }

    @DELETE
    @Path("/{id}")
    public ResponseDto<String> delete(@PathParam("id") Long id) {
        customerService.delete(id);
        return new ResponseDto<>("CUSTOMER_DELETE_SUCCESSFULLY");
    }

    @GET
    @Path("/{id}")
    public ResponseDto<CustomerDto> findById(@PathParam("id") Long id) {
        Customer customer = customerService.findById(id);
        return new ResponseDto<>(
                customerMapper.toDto(customer)
        );
    }

    @GET
    public ResponseDto<CustomerDto> lookup(@QueryParam("accountNumber") String accountNumber) {
        Customer customer = customerService.findByAccountNumber(accountNumber);
        return new ResponseDto<>(
                customerMapper.toDto(customer)
        );
    }
}
