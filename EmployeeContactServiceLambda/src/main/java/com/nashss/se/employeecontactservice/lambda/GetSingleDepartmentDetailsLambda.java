package com.nashss.se.employeecontactservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.employeecontactservice.activity.requests.GetSingleDepartmentDetailsRequest;
import com.nashss.se.employeecontactservice.activity.results.GetSingleDepartmentDetailsResult;

public class GetSingleDepartmentDetailsLambda extends LambdaActivityRunner<GetSingleDepartmentDetailsRequest,
        GetSingleDepartmentDetailsResult> implements RequestHandler<LambdaRequest<GetSingleDepartmentDetailsRequest>, LambdaResponse> {


    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetSingleDepartmentDetailsRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetSingleDepartmentDetailsRequest.builder()
                                .withDeptId(path.get("deptId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetSingleDepartmentDetailsActivity().handleRequest(request)
        );
    }
}
