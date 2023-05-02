package ma.enset.ebankbackend.dtos;

import lombok.Data;
import ma.enset.ebankbackend.entities.AccountOperation;

import java.util.List;
@Data
public class AccountHistoryDTO {
    private String accountId;
    private double balance;
    private int currentPage;
    private int totalPages;
    private  int pageSize;
    private List<AccountOperationDTO> accountOperationDTOS;
}
