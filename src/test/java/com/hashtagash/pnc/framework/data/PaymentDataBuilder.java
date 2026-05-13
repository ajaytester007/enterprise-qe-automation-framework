package com.hashtagash.pnc.framework.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class PaymentDataBuilder {
    private String loanId = "LN-" + UUID.randomUUID();
    private BigDecimal amount = new BigDecimal("125.50");
    private LocalDate effectiveDate = LocalDate.now();
    private String channel = "ONLINE";
    public PaymentDataBuilder withLoanId(String id) { this.loanId = id; return this; }
    public PaymentDataBuilder withAmount(String amt) { this.amount = new BigDecimal(amt); return this; }
    public PaymentData build() { return new PaymentData(loanId, amount, effectiveDate, channel); }
    public record PaymentData(String loanId, BigDecimal amount, LocalDate effectiveDate, String channel) {}
}
