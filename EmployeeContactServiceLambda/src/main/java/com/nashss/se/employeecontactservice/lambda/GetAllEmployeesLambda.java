package com.nashss.se.employeecontactservice.lambda;

import com.nashss.se.employeecontactservice.activity.requests.GetAllEmployeesRequest;
import com.nashss.se.employeecontactservice.activity.results.GetAllEmployeesResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetAllEmployeesLambda extends LambdaActivityRunner<GetAllEmployeesRequest, GetAllEmployeesResult>
        implements RequestHandler<LambdaRequest<GetAllEmployeesRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetAllEmployeesRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPath(path ->
                    GetAllEmployeesRequest.builder()
                            .withEmployeeId(path.get("employeeId"))
                            .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideGetAllEmployeesActivity().handleRequest(request)
        );
    }
}
