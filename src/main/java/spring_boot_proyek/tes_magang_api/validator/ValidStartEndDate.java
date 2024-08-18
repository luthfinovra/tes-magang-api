//package spring_boot_proyek.tes_magang_api.validator;
//
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//@Constraint(validatedBy = StartEndDateValidator.class)
//@Target({ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//public @interface ValidStartEndDate {
//    String message() default "Tanggal Mulai proyek harus sebelum Tanggal Selesai";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}