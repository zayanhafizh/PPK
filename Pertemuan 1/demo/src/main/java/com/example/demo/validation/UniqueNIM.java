//package com.example.demo.validation;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//@Constraint(validatedBy = UniqueValidator.class)
//@Target({ ElementType.FIELD, ElementType.METHOD })
//@Retention(RetentionPolicy.RUNTIME)
//public @interface UniqueNIM {
//    String message() default "Value must be unique";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}
