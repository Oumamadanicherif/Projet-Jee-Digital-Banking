package ma.enset.ebankbackend;

import jakarta.transaction.Transactional;
import ma.enset.ebankbackend.dtos.BankAccountDTO;
import ma.enset.ebankbackend.dtos.CurrentBankAccountDTO;
import ma.enset.ebankbackend.dtos.CustomerDTO;
import ma.enset.ebankbackend.dtos.SavingBankAccountDTO;
import ma.enset.ebankbackend.entities.*;
import ma.enset.ebankbackend.enums.AccountStatus;
import ma.enset.ebankbackend.enums.OperationType;
import ma.enset.ebankbackend.exceptions.BalanceNotSufficientException;
import ma.enset.ebankbackend.exceptions.BankAccountNotFoundException;
import ma.enset.ebankbackend.exceptions.CustomerNotFoundException;
import ma.enset.ebankbackend.repositories.AccountOperationRepository;
import ma.enset.ebankbackend.repositories.BankAccountRepository;
import ma.enset.ebankbackend.repositories.CustomerRepository;
import ma.enset.ebankbackend.services.BankAccountService;
import ma.enset.ebankbackend.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(EbankBackendApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
		return args -> {
			Stream.of("Oumnia","Ayoub","Salim").forEach(name -> {
				CustomerDTO customer = new CustomerDTO();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				bankAccountService.saveCustomer(customer);
			});
			bankAccountService.listCustomers().forEach(customer -> {
				try {
					bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000, customer.getId());
					bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5, customer.getId());
				} catch (CustomerNotFoundException e) {
					e.printStackTrace();
				}
			});
			List<BankAccountDTO> bankAccounts=bankAccountService.bankAccountList();
			for (BankAccountDTO bankAccount: bankAccounts) {
				for (int i = 0; i < 10; i++) {
					String accountId;
					if(bankAccount instanceof SavingBankAccountDTO){
						accountId=((SavingBankAccountDTO)bankAccount).getId();
					} else{
						accountId=((CurrentBankAccountDTO)bankAccount).getId();
					}
					bankAccountService.credit(accountId, 10000 + Math.random() * 120000, "Credit");
					bankAccountService.debit(accountId, 1000+Math.random()*9000,"Debit");
				}
			}
		};
	}
	//@Bean
    CommandLineRunner start(CustomerRepository customerRepository,
							BankAccountRepository bankAccountRepository,
							AccountOperationRepository accountOperationRepository){
        return args -> {
		Stream.of("Oumaima","Mohammed","Salma").forEach(name -> {
			Customer customer = new Customer();
			customer.setName(name);
			customer.setEmail(name+"@gmail.com");
			customerRepository.save(customer);
		});
       customerRepository.findAll().forEach(customer -> {
		   CurrentAccount currentAccount = new CurrentAccount();
		   currentAccount.setId(UUID.randomUUID().toString());
		   currentAccount.setBalance(Math.random()*90000);
		   currentAccount.setCreatedAt(new Date());
		   currentAccount.setStatus(AccountStatus.CREATED);
		   currentAccount.setCustomer(customer);
		   currentAccount.setOverDraft(9000);
		   bankAccountRepository.save(currentAccount);
		   SavingAccount savingAccount = new SavingAccount();
		   savingAccount.setId(UUID.randomUUID().toString());
		   savingAccount.setBalance(Math.random()*90000);
		   savingAccount.setCreatedAt(new Date());
		   savingAccount.setStatus(AccountStatus.CREATED);
		   savingAccount.setCustomer(customer);
		   savingAccount.setInterestRate(5.5);
		   bankAccountRepository.save(savingAccount);
	   });
	   bankAccountRepository.findAll().forEach(acc -> {
		   for (int i = 0; i < 10; i++) {
			   AccountOperation accountOperation= new AccountOperation();
			   accountOperation.setOperationDate(new Date());
			   accountOperation.setAmount(Math.random()*12000);
			   accountOperation.setType(Math.random()>0.5? OperationType.DEBIT: OperationType.CREDIT );
			   accountOperation.setBankAccount(acc);
			   accountOperationRepository.save(accountOperation);
		   }
	   });

	};
	}
}
