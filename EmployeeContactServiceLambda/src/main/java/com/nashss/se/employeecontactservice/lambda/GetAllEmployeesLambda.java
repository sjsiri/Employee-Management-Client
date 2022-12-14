package com.nashss.se.employeecontactservice.lambda;

import com.nashss.se.employeecontactservice.activity.requests.GetAllEmployeesRequest;
import com.nashss.se.employeecontactservice.activity.results.GetAllEmployeesResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.HashMap;

import static com.nashss.se.employeecontactservice.utils.NullUtils.ifNull;

public class GetAllEmployeesLambda extends LambdaActivityRunner<GetAllEmployeesRequest, GetAllEmployeesResult>
        implements RequestHandler<LambdaRequest<GetAllEmployeesRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetAllEmployeesRequest> input, Context context) {
        return super.runActivity(() ->
                    input.fromPath(path ->
                            GetAllEmployeesRequest.builder()
                                    .withLastNameEmployeeId(path.get("employeeId"))
                                    .withDeptId(
                                            (ifNull(input.getQueryStringParameters(), new HashMap<String, String>())
                                            ).get("deptId"))
                                    .withForwardBoolean(Boolean.parseBoolean(path.get("forward")))
                                    .build()), (request, serviceComponent) ->
                    serviceComponent.provideGetAllEmployeesActivity().handleRequest(request)
        );
    }
}
