package com.nashss.se.employeecontactservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.employeecontactservice.activity.requests.CreateEmployeeRequest;
import com.nashss.se.employeecontactservice.activity.results.CreateEmployeeResult;

public class CreateEmployeeLambda extends LambdaActivityRunner<CreateEmployeeRequest, CreateEmployeeResult>
        implements RequestHandler<LambdaRequest<CreateEmployeeRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateEmployeeRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromBody(CreateEmployeeRequest.class),
                (request, serviceComponent) ->
                        serviceComponent.provideCreateEmployeeActivity().handleRequest(request)
        );
    }
}
