package com.iy.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;
import com.iy.dao.AccountRepository;
import com.iy.dto.Account;
import com.iy.exception.AtmException;
import com.iy.service.TransactionServiceImpl;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {
	
	@InjectMocks
	private TransactionServiceImpl transService;
	
	@Mock
	private AccountRepository accRepository;
	
    @Test
    public void testDepositCash() 
    {
        Optional<Account> acc = Optional.of(new Account("120","Raj","Singh","raj12@gmail.com","9009876123","Active",12.37));
        
		when(accRepository.findById(acc.get().getAccNumber()))
		.thenReturn(acc);
        
        try {
			transService.depositCash(acc.get().getAccNumber(), 12.0);
		} catch (AtmException e) {
			e.printStackTrace();
		}
                
        Mockito.verify(accRepository, Mockito.times(1)).save(acc.get());
    }
    
    @Test
    public void testWithdrawCash() 
    {
    	Optional<Account> acc = Optional.of(new Account("120","Raj","Singh","raj12@gmail.com","9009876123","Active",12.37));
        
		when(accRepository.findById(acc.get().getAccNumber()))
		.thenReturn(acc);
		
        try {
			transService.withdrawCash(acc.get().getAccNumber(), 12.0);
		} catch (AtmException e) {
			e.printStackTrace();
		}
        
        Mockito.verify(accRepository, Mockito.times(1)).save(acc.get());
    }
    
    @Test
    public void testGetBalance() 
    {
    	Optional<Account> acc = Optional.of(new Account("120","Raj","Singh","raj12@gmail.com","9009876123","Active",12.37));
        
		when(accRepository.findById(acc.get().getAccNumber()))
		.thenReturn(acc);
		
		double result = 0;
		try {
			result = transService.getBalance(acc.get().getAccNumber());
		} catch (AtmException e) {
			e.printStackTrace();
		}
        
        assertThat(result).isEqualTo(acc.get().getBalance());
    }

}
