package model.service;

import java.util.Calendar;
import java.util.Date;

import model.entites.Contract;
import model.entites.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentlService;
	
	public ContractService(OnlinePaymentService onlinePaymentlService) {
		this.onlinePaymentlService = onlinePaymentlService;
	}
	
	public void processContract(Contract contract, int months) {
		double basicQuota = contract.getTotalValue() / months;
		for (int i = 1; i<=months; i++) {
			double updateQuota = basicQuota + onlinePaymentlService.interest(basicQuota, i);
			double fullQuota = basicQuota + onlinePaymentlService.paymentFee(updateQuota);
			Date dueDate = addMonths(contract.getDate(), i);
			contract.getInstallment().add(new Installment(dueDate, fullQuota));
			
		}
	}
	
	@SuppressWarnings("static-access")
	private Date addMonths(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.MONTH, months);
		return calendar.getTime();	
	}
}
