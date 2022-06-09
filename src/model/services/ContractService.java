package model.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onLinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onLinePaymentService = onlinePaymentService;
	}
	
	public void processContract(Contract contract, int months) {
		
		double basicQuota = contract.getTotalValue() / months;
		for(int i=1; i<=months; i++) {
			double updatedQuota = basicQuota + onLinePaymentService.interest(basicQuota, i);
			double fullQuota = updatedQuota + onLinePaymentService.paymentFee(updatedQuota);
			Date dueDate = addMonth(contract.getDate(), i);
			contract.getInstallments().add(new Installment(dueDate, fullQuota));
		}
	}
	
	private Date addMonth(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, n);
		return calendar.getTime();
	}

}
