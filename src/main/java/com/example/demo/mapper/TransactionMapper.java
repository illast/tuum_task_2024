package com.example.demo.mapper;

import com.example.demo.dto.TransactionCreateDTO;
import com.example.demo.dto.TransactionDTO;
import com.example.demo.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

    @Mapping(source = "account.accountId", target = "accountId")
    TransactionDTO toDto(Transaction transaction);

    @Mapping(source = "account.accountId", target = "accountId")
    TransactionCreateDTO toCreateDto(Transaction transaction);

    Transaction toEntity(TransactionDTO transactionDTO);

    List<TransactionDTO> toDtoList(List<Transaction> transactions);

    List<Transaction> toEntityList(List<TransactionDTO> transactionDTOS);
}
