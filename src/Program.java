import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Contract data: ");
		System.out.print("Contract number: ");
		int number = sc.nextInt();
		System.out.print("Contract date:");
		sc.nextLine();
		Date date = sdf.parse(sc.nextLine());
		System.out.print("Contract value:");
		double contractValue = sc.nextDouble();
		
		System.out.print("Number of installments: ");
		int installments = sc.nextInt();
		
		Contract contract = new Contract(number, date, contractValue);
		ContractService cs = new ContractService(new PaypalService());
		cs.processContract(contract, installments);
		
		System.out.println("Installments: ");
		for(Installment i : contract.getInstallments()) {
			System.out.println(i);
		}
	}

}
