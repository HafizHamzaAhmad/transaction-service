package org.rak.transaction.unit.transaction;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.rak.transaction.TransactionApplication;
import org.rak.transaction.exception.ApplicationException;
import org.rak.transaction.interfaces.BusinessService;
import org.rak.transaction.interfaces.Mapper;
import org.rak.transaction.interfaces.RequestValidator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.rak.transaction.Constats.Constants.CONTACT_EMAIL;
import static org.rak.transaction.Constats.Constants.FEE_TYPE_TUITION;

@Service
public class TransactionService implements BusinessService<TransactionDto> {

    private final RequestValidator<TransactionDto> validator;
    private final Mapper<TransactionDto, Transaction> mapper;
    private final TransactionRepository repository;


    public TransactionService(RequestValidator<TransactionDto> validator, Mapper<TransactionDto, Transaction> mapper, TransactionRepository repository) {
        this.validator = validator;
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public TransactionDto getByUuid(String uuid) {
        return null;
    }

    @Override
    public TransactionDto create(TransactionDto dto) {
        return Optional.ofNullable(dto)
                .filter(validator::validateRequest)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::toDto)
                .orElseThrow(() -> new ApplicationException("100-001", "Unable to create"));
    }

    @Override
    public TransactionDto update(TransactionDto dto, String id) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    public List<TransactionDto> getAllByStudentId(String studentId) {
        return repository.findAllByStudentId(studentId).stream().map(mapper::toDto).toList();
    }

    private TransactionDto getByTransID(String transRefNum) {
        Optional<Transaction> transaction = repository.findFirstByTransRefNum(transRefNum);
        return transaction.map(mapper::toDto).orElse(null);
    }

    public String generateReceiptByRefNo(String transNumber) {
        TransactionDto transactionDto = getByTransID(transNumber);

        if (transactionDto != null) {
            PaymentReceiptJasperDTO paymentReceiptJasperDTO = PaymentReceiptJasperDTO.builder()
                    .studentName(transactionDto.getStudentName())
                    .studentGrade(transactionDto.getGrade())
                    .guardianName(transactionDto.getGuardianName())
                    .feeType(FEE_TYPE_TUITION)
                    .amount(transactionDto.getAmount())
                    .contactEmail(CONTACT_EMAIL)
                    .studentId(transactionDto.getStudentId())
                    .cardType(transactionDto.getCardType())
                    .cardNumber(transactionDto.getCardNumber())
                    .txnRefNo(transactionDto.getTransRefNum())
                    .txnDate(transactionDto.getTransDateTime())
                    .build();

            return generatePDFPaymentReceipt(paymentReceiptJasperDTO, "test", "/templates/paymentReceipt.jrxml");
        } else {
            return "Transaction not found";
        }
    }


    String generatePDFPaymentReceipt(PaymentReceiptJasperDTO paymentReceiptJasperDTO, String fileName, String template) {
        JRDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(paymentReceiptJasperDTO));
        InputStream inputStream = TransactionApplication.class.getResourceAsStream(template);

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

            File pdf = File.createTempFile(fileName, ".pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(pdf));
            Path path = Paths.get(pdf.toURI());
            return path.toUri().toURL().toString();

        } catch (IOException | JRException e) {
            throw new ApplicationException("", e.getMessage());
        }
    }
}
