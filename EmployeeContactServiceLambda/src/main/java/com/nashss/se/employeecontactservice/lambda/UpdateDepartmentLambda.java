package com.nashss.se.employeecontactservice.lambda;
import com.nashss.se.employeecontactservice.activity.requests.UpdateDepartmentRequest;
import com.nashss.se.employeecontactservice.activity.results.UpdateDepartmentResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

import static com.nashss.se.employeecontactservice.utils.NullUtils.ifNull;


public class UpdateDepartmentLambda extends LambdaActivityRunner<UpdateDepartmentRequest, UpdateDepartmentResult>
        implements RequestHandler<LambdaRequest<UpdateDepartmentRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<UpdateDepartmentRequest> input, Context context) {
        return super.runActivity(
            () -> {
                UpdateDepartmentRequest updateDepartmentRequest = input.fromBody(UpdateDepartmentRequest.class);
                Map<String, String> path = ifNull(input.getPathParameters(), Map.of());
                updateDepartmentRequest.setPathDeptId(path.get("deptId"));
                return updateDepartmentRequest;
            },
            (request, serviceComponent) ->
                    serviceComponent.provideUpdateDepartmentActivity().handleRequest(request)
        );
    }
}


