package com.example.demo.mapper;

import com.example.demo.dto.AccountDTO;
import com.example.demo.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    AccountDTO toDto(Account account);

    Account toEntity(AccountDTO accountDTO);

    List<AccountDTO> toDtoList(List<Account> accounts);

    List<Account> toEntityList(List<AccountDTO> accountDTOS);
}
