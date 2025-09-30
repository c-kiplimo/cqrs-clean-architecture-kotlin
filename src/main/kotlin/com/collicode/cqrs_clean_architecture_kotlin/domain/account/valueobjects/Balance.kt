package com.collicode.cqrs_clean_architecture_kotlin.domain.account.valueobjects


data class Balance(val amount: Long = 0, val balanceCurrency: BalanceCurrency = BalanceCurrency.USD)
