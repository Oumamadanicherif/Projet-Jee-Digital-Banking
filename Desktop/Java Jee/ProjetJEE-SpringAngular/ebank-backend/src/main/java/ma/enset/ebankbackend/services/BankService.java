package ma.enset.ebankbackend.services;


import jakarta.transaction.Transactional;
import ma.enset.ebankbackend.entities.BankAccount;
import ma.enset.ebankbackend.entities.CurrentAccount;
import ma.enset.ebankbackend.entities.SavingAccount;
import ma.enset.ebankbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
public void consulter(){
    BankAccount bankAccount= bankAccountRepository.findById("1090424b-e25f-4f1d-91c8-46e2dfa74783").orElse(null);
    if(bankAccount !=null) {
        System.out.println("**********************************************");
        System.out.println(bankAccount.getId());
        System.out.println(bankAccount.getBalance());
        System.out.println(bankAccount.getStatus());
        System.out.println(bankAccount.getCreatedAt());
        System.out.println(bankAccount.getCustomer().getName());
        System.out.println(bankAccount.getClass().getSimpleName());
        if (bankAccount instanceof CurrentAccount) {
            System.out.println("OverDraft ===> " + ((CurrentAccount) bankAccount).getOverDraft());
        } else if (bankAccount instanceof SavingAccount) {
            System.out.println("Rate ===> " + ((SavingAccount) bankAccount).getInterestRate());
        }
        bankAccount.getAccountOperations().forEach(op -> {
            System.out.println(op.getType() + "\t" + op.getOperationDate() + "\t" + op.getAmount());
        });
    }

}



}

