package com.nashss.se.employeecontactservice.lambda;

import com.nashss.se.employeecontactservice.activity.requests.CreateDepartmentRequest;
import com.nashss.se.employeecontactservice.activity.results.CreateDepartmentResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateDepartmentLambda extends LambdaActivityRunner<CreateDepartmentRequest, CreateDepartmentResult>
        implements RequestHandler<LambdaRequest<CreateDepartmentRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateDepartmentRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromBody(CreateDepartmentRequest.class),
            (request, serviceComponent) ->
                    serviceComponent.provideCreateDepartmentActivity().handleRequest(request)
        );
    }
}
