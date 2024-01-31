package org.rak.transaction.unit.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class TransactionDto {
    @JsonProperty("uuid")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String uuid;

    @JsonProperty("studentName")
    private String studentName;

    @JsonProperty("studentId")
    private String studentId;

    @JsonProperty("guardianName")
    private String guardianName;

    @JsonProperty("schoolName")
    private String schoolName;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("schoolLogoUrl")
    private String schoolLogoUrl;

    @JsonProperty("cardNumber")
    private String cardNumber;

    @JsonProperty("cardType")
    private String cardType;

    @JsonProperty("transRefNum")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String transRefNum;

    @JsonProperty("grade")
    private String grade;

    @JsonProperty("transDateTime")
    private String transDateTime;
}
