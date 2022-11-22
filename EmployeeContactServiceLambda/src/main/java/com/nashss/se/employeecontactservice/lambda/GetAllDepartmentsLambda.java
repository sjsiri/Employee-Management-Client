package com.nashss.se.employeecontactservice.lambda;

import com.nashss.se.employeecontactservice.activity.requests.GetAllDepartmentsRequest;
import com.nashss.se.employeecontactservice.activity.results.GetAllDepartmentsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetAllDepartmentsLambda extends LambdaActivityRunner<GetAllDepartmentsRequest, GetAllDepartmentsResult>
        implements RequestHandler<LambdaRequest<GetAllDepartmentsRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetAllDepartmentsRequest> input, Context context) {
        return super.runActivity(() -> input.fromPath(path ->
                        GetAllDepartmentsRequest.builder()
                                .withDeptId(path.get("deptId"))
                                .build()), (request, serviceComponent) ->
                        serviceComponent.provideGetAllDepartmentsActivity().handleRequest(request)
        );
    }
}
