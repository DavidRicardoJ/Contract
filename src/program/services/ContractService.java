package program.services;

import program.entities.Contract;
import program.entities.Installment;

import java.util.Calendar;
import java.util.Date;

public class ContractService {
    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }
    public void processContract (Contract contract, int months){
        double basicQuota = contract.getTotalValue() / months;
        for (int i = 1; i <= months; i++) {
            Date date = addMonths(contract.getDate(),i);
            double updateQuota = basicQuota + onlinePaymentService.interest(basicQuota,i);
            double fullQuota = updateQuota + onlinePaymentService.paymentFee(updateQuota);
            contract.addInstallments(new Installment(date,fullQuota));
        }
    }
    private Date addMonths(Date date, int n){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,n);
        return calendar.getTime();
    }
}
