package com.nashss.se.employeecontactservice.lambda;

import com.nashss.se.employeecontactservice.activity.requests.GetSingleEmployeeDetailsRequest;
import com.nashss.se.employeecontactservice.activity.results.GetSingleEmployeeDetailsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetSingleEmployeeDetailsLambda extends LambdaActivityRunner<GetSingleEmployeeDetailsRequest,
        GetSingleEmployeeDetailsResult> implements RequestHandler<LambdaRequest<GetSingleEmployeeDetailsRequest>,
        LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetSingleEmployeeDetailsRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPath(path ->
                    GetSingleEmployeeDetailsRequest.builder()
                            .withEmployeeId(path.get("employeeId"))
                            .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideGetSingleEmployeeDetailsActivity().handleRequest(request)
        );
    }
}
