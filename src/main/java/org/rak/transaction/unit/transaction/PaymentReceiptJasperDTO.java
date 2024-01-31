package org.rak.transaction.unit.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentReceiptJasperDTO {
    private String studentName;
    private String studentGrade;
    private String guardianName;
    private String feeType;
    private String amount;
    private String contactEmail;
    private String studentId;
    private String cardType;
    private String cardNumber;
    private String txnRefNo;
    private String txnDate;
}
