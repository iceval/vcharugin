package org.rosatom.vcharugin.exceptions;

import com.google.protobuf.Any;
import com.google.rpc.Code;
import com.google.rpc.Status;
import org.rosatom.vcharugin.ModelExceptionResponse;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.StatusProto;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ModelExceptionHandler {

    @GrpcExceptionHandler(ModelException.class)
    public StatusRuntimeException handleValidationError(ModelException modelException) {

        ModelExceptionResponse exceptionResponse =
                ModelExceptionResponse.newBuilder()
                        .setErrorCode(modelException.getErrorCode())
                        .build();


        Status status = Status
                .newBuilder()
                .setCode(Code.INVALID_ARGUMENT.getNumber())
                .setMessage(modelException.getMessage())
                .addDetails(Any.pack(exceptionResponse))
                .build();

        return StatusProto.toStatusRuntimeException(status);
    }
}
