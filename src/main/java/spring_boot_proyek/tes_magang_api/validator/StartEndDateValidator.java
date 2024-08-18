//package spring_boot_proyek.tes_magang_api.validator;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//public class StartEndDateValidator implements ConstraintValidator<ValidStartEndDate, CreateProyekRequest> {
//    @Override
//    public boolean isValid(CreateProyekRequest request, ConstraintValidatorContext context) {
//        if (request.getTglMulai() == null || request.getTglSelesai() == null) {
//            return true;
//        }
//        return request.getTglMulai().isBefore(request.getTglSelesai());
//    }
//}
