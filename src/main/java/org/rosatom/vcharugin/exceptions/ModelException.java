package org.rosatom.vcharugin.exceptions;

import lombok.Getter;
import org.rosatom.vcharugin.ModelErrorCode;

@Getter
public class ModelException extends RuntimeException {
    private ModelErrorCode errorCode;

    public ModelException(ModelErrorCode errorCode) {
        super(errorCode.name());
        this.errorCode = errorCode;
    }
}
