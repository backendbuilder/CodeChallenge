package com.rabobank.accountservice.domain.dtos;

import java.math.BigDecimal;

public record TransferDto(long from, long to, BigDecimal amount){
}
