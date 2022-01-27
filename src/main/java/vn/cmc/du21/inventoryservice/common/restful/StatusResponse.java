package vn.cmc.du21.inventoryservice.common.restful;

public enum StatusResponse {
    SUCCESSFUL("Successful", 200), CREATED("Created", 201), ACCEPTED("Accepted", 202)
    , NO_CONTENT("No Content", 204), BAD_REQUEST("Bad Request", 400), UNAUTHORIZED("UnAuthorized", 401)
    , FORBIDDEN("Forbidden", 403), NOT_FOUND("Not Found", 404), INTERNAL_SERVER_ERROR("Internal Server Error", 500);

    final private String status;

    StatusResponse(String status, Integer statusInt) {
        this.status = status;
        this.statusInt = statusInt;
    }

    public String getStatus() {
        return status;
    }

    final private Integer statusInt;

    public Integer getStatusInt() {
        return statusInt;
    }
}
