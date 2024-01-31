package org.rak.transaction.unit.transaction;

import org.apache.logging.log4j.util.Strings;
import org.rak.transaction.annotation.Validator;
import org.rak.transaction.exception.ApplicationException;
import org.rak.transaction.interfaces.RequestValidator;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Validator
public class TransactionRequestValidatorImpl implements RequestValidator<TransactionDto> {


    @Override
    public boolean validateRequest(TransactionDto dto) {
        Optional.ofNullable(dto)
                .orElseThrow(() -> new ApplicationException(HttpStatus.BAD_REQUEST.name(), "invalid request"));
        Optional.ofNullable(dto.getCardNumber())
                .filter(Strings::isNotBlank)
                .orElseThrow(() -> new ApplicationException(HttpStatus.BAD_REQUEST.name(), "cardNumber is mandatory"));
        Optional.ofNullable(dto.getAmount())
                .filter(Strings::isNotBlank)
                .orElseThrow(() -> new ApplicationException(HttpStatus.BAD_REQUEST.name(), "amount is mandatory"));

        Optional.ofNullable(dto.getGrade())
                .filter(Strings::isNotBlank)
                .orElseThrow(() -> new ApplicationException(HttpStatus.BAD_REQUEST.name(), "grade is mandatory"));
        Optional.ofNullable(dto.getGuardianName())
                .filter(Strings::isNotBlank)
                .orElseThrow(() -> new ApplicationException(HttpStatus.BAD_REQUEST.name(), "guardianName is mandatory"));
        Optional.ofNullable(dto.getStudentId())
                .filter(Strings::isNotBlank)
                .orElseThrow(() -> new ApplicationException(HttpStatus.BAD_REQUEST.name(), "studentId is mandatory"));
        Optional.ofNullable(dto.getStudentName())
                .filter(Strings::isNotBlank)
                .orElseThrow(() -> new ApplicationException(HttpStatus.BAD_REQUEST.name(), "studentName is mandatory"));
        Optional.ofNullable(dto.getSchoolLogoUrl())
                .filter(Strings::isNotBlank)
                .orElseThrow(() -> new ApplicationException(HttpStatus.BAD_REQUEST.name(), "logoUrl is mandatory"));


        return true;
    }

    private boolean validateSubTypeBasedOnType(String type, String subType) {
        return true;
    }
}
