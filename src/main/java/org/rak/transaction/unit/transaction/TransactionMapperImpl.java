package org.rak.transaction.unit.transaction;

import org.rak.transaction.interfaces.Mapper;
import org.rak.transaction.util.Utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.rak.transaction.util.Utility.removeSpaces;

@org.rak.transaction.annotation.Mapper
public class TransactionMapperImpl implements Mapper<TransactionDto, Transaction> {
    @Override
    public TransactionDto toDto(Transaction entity) {
        return TransactionDto.builder()
                .uuid(entity.getUuid())
                .studentName(entity.getStudentName())
                .studentId(entity.getStudentId())
                .guardianName(entity.getGuardianName())
                .schoolName(entity.getSchoolName())
                .amount(entity.getAmount())
                .schoolLogoUrl(entity.getSchoolLogoUrl())
                .cardNumber(entity.getCardNumber())
                .cardType(entity.getCardType())
                .transRefNum(entity.getTransRefNum())
                .grade(entity.getGrade())
                .transDateTime(entity.getTransDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();

    }

    @Override
    public Transaction toEntity(TransactionDto dto) {
        return Transaction.builder()
                .uuid(UUID.randomUUID().toString())
                .studentName(dto.getStudentName())
                .studentId(dto.getStudentId())
                .guardianName(dto.getGuardianName())
                .schoolName(dto.getSchoolName())
                .amount(dto.getAmount())
                .schoolLogoUrl(dto.getSchoolLogoUrl())
                .cardNumber(dto.getCardNumber())
                .cardType(Utility.detectCreditCardType(removeSpaces(dto.getCardNumber())))
                .transRefNum("TRX-".concat(UUID.randomUUID().toString()).substring(0,7))
                .grade(dto.getGrade())
                .transDateTime(LocalDateTime.now())
                .build();

    }
}
