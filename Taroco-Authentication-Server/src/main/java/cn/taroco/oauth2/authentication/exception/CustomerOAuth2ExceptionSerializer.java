package cn.taroco.oauth2.authentication.exception;

import cn.taroco.oauth2.authentication.common.Response;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * CustomerOAuth2Exception 序列化实现
 *
 * @author liuht
 * 2019/5/6 10:29
 */
public class CustomerOAuth2ExceptionSerializer extends StdSerializer<CustomerOAuth2Exception> {

    public CustomerOAuth2ExceptionSerializer() {
        super(CustomerOAuth2Exception.class);
    }

    @Override
    public void serialize(final CustomerOAuth2Exception e, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("errorCode", "0006");
        jsonGenerator.writeStringField("errorMessage", e.getOAuth2ErrorCode() + ":" + e.getMessage());
        jsonGenerator.writeStringField("status", Response.Status.FAILED.name());
        jsonGenerator.writeEndObject();
    }
}
