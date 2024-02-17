package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by jdev on 05.05.2017.
 * класс для отправки ответа на POST запрос
 */

public class Response {

    Boolean success;

    public Response(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean message) {
        this.success = success;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}