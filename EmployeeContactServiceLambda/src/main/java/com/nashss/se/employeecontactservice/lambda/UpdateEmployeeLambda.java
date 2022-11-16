package com.nashss.se.employeecontactservice.lambda;

import com.nashss.se.employeecontactservice.activity.requests.UpdateEmployeeRequest;
import com.nashss.se.employeecontactservice.activity.results.UpdateEmployeeResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

import static com.nashss.se.employeecontactservice.utils.NullUtils.ifNull;

public class UpdateEmployeeLambda
        extends LambdaActivityRunner<UpdateEmployeeRequest, UpdateEmployeeResult>
        implements RequestHandler<LambdaRequest<UpdateEmployeeRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<UpdateEmployeeRequest> input, Context context) {
        return super.runActivity(
            () -> {
                UpdateEmployeeRequest updateEmployeeRequest = input.fromBody(UpdateEmployeeRequest.class);
                Map<String, String> path = ifNull(input.getPathParameters(), Map.of());
                updateEmployeeRequest.setPathEmployeeId(path.get("employeeId"));
                return updateEmployeeRequest;
            },
            (request, serviceComponent) ->
                    serviceComponent.provideUpdateEmployeeActivity().handleRequest(request)
        );
    }
}
