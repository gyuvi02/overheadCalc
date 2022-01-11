package org.gyula.overheadCalc.util;

import org.gyula.overheadCalc.entity.Users;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DoValidate {

    public static  List<String> validate(Users user) {

        List<String> errors = new ArrayList();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Users>> cvs = validator.validate(user);

        if (!cvs.isEmpty()) {

            for (ConstraintViolation<Users> cv : cvs) {

                StringBuilder err = new StringBuilder();
                err.append(cv.getPropertyPath());
                err.append(" ");
                err.append(cv.getMessage());
                errors.add(err.toString());
            }
        }

        return errors;
    }
}
