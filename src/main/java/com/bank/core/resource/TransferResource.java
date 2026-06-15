package com.bank.core.resource;

import com.bank.core.dto.ResponseDto;
import com.bank.core.dto.TransferInfoDto;
import com.bank.core.dto.TransferRequest;
import com.bank.core.dto.TransferResponse;
import com.bank.core.service.TransferService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/bank/core/transfers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransferResource {

    @Inject
    TransferService transferService;

    @POST
    public ResponseDto<TransferResponse> transfer(TransferRequest request,
                                                  @HeaderParam("X-National-Code") String currentUserNationalCode) {
        return new ResponseDto<>(
                transferService.transfer(request, currentUserNationalCode)
        );
    }

    @POST
    @Path("/history")
    public ResponseDto<List<TransferInfoDto>> history(@HeaderParam("X-National-Code") String currentUserNationalCode) {
        return new ResponseDto<>(
                transferService.history(currentUserNationalCode)
        );
    }
}

