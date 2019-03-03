package program;

import program.entities.Contract;
import program.entities.Installment;
import program.services.ContractService;
import program.services.PaypalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Enter contract data");
        System.out.print("Number: ");
        int number = sc.nextInt();
        System.out.print("Date (dd/mm/yyyy): ");
        Date date = simpleDateFormat.parse(sc.next());
        System.out.print("Contract value: ");
        double totalValue= sc.nextDouble();

        Contract contract = new Contract(number,date,totalValue);

        System.out.print("Enter number of installments: ");
        int n= sc.nextInt();

        ContractService contractService = new ContractService(new PaypalService());
        contractService.processContract(contract,n);
        System.out.println("Installments: ");

        for (Installment x: contract.getInstallments()) {
            System.out.println(x);
        }
        sc.close();
    }
}
