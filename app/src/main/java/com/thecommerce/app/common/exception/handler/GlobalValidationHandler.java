package com.thecommerce.app.common.exception.handler;

import com.thecommerce.app.common.exception.ValidationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Aspect
@Component
public class GlobalValidationHandler {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void patchMapping() {
    }

    @Before("postMapping() || patchMapping()")
    public void validationAdvice(final JoinPoint jp) {
        List<Object> args = Arrays.stream(jp.getArgs()).collect(Collectors.toList());
        List<Errors> errors = extractErrors(args);
        for (Errors error : errors) {
            if (error.hasErrors()) {
                List<FieldError> fieldErrors = error.getFieldErrors();
                String errorMessage = parsingErrorMessages(fieldErrors);
                throw new ValidationException(errorMessage);
            }
        }
    }

    private List<Errors> extractErrors(final List<Object> args) {
        return args.stream()
                .filter(Errors.class::isInstance)
                .map(Errors.class::cast)
                .collect(Collectors.toList());
    }

    private String parsingErrorMessages(final List<FieldError> fieldErrors) {
        List<String> errorMessages = fieldErrors.stream()
                .map(error -> error.getDefaultMessage() + ":" + error.getField())
                .collect(Collectors.toList());
        return errorMessages.toString().replace("[", "").replace("]", "");
    }
}
