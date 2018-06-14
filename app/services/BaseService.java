package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

public class BaseService {

    public Boolean isValidRequest(JsonNode request, String... validationParams) {
        Boolean valid = true;
        for (String param : validationParams) {
            valid = valid && request.hasNonNull(param);
        }
        return valid;
    }

    public ObjectNode getSuccessJson() {
        return Json.newObject().put("status", "true");
    }

    public ObjectNode getErrorJson() {
        return Json.newObject().put("status", "false");
    }

}
