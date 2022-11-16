package com.nashss.se.employeecontactservice.lambda;

import com.nashss.se.employeecontactservice.activity.requests.UpdateEmployeeRequest;
import com.nashss.se.employeecontactservice.activity.results.UpdateEmployeeResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateEmployeeLambda
        extends LambdaActivityRunner<UpdateEmployeeRequest, UpdateEmployeeResult>
        implements RequestHandler<LambdaRequest<UpdateEmployeeRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<UpdateEmployeeRequest> input, Context context) {
        return super.runActivity(
            () -> {
                UpdateEmployeeRequest updateEmployeeRequest = input.fromBody(UpdateEmployeeRequest.class);
                UpdateEmployeeRequest updateEmployeeRequest2 = input.fromPath(path ->
                          UpdateEmployeeRequest.builder()
                                .withEmployeeId(path.get("employeeId"))
                                .build());
                updateEmployeeRequest.setPathEmployeeId(updateEmployeeRequest2.getEmployeeId());
                return updateEmployeeRequest;
            },
            (request, serviceComponent) ->
                    serviceComponent.provideUpdateEmployeeActivity().handleRequest(request)
        );
    }
}
