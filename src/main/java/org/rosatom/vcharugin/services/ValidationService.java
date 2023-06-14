package org.rosatom.vcharugin.services;

import org.rosatom.vcharugin.ModelErrorCode;
import org.rosatom.vcharugin.exceptions.ModelException;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public void validateName(String name) {
        checkIfValidName(name);
    }
    public void validateComment(String comment) {
        checkIfValidComment(comment);
    }

    private void checkIfValidName(String name) {
        if (name.length() < 3 || name.length() >= 20) {
            throw new ModelException(ModelErrorCode.INVALID_NAME);
        }
    }

    private void checkIfValidComment(String comment) {
        if (comment.length() < 1 || comment.length() >= 100) {
            throw new ModelException(ModelErrorCode.INVALID_COMMENT);
        }
    }
}