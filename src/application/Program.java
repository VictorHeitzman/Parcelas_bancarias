package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entites.Contract;
import model.entites.Installment;
import model.service.ContractService;
import model.service.PaypalService;

public class Program {
		public static void main(String[] args) throws ParseException {
			
			Locale.setDefault(Locale.US);
			Scanner sc = new Scanner(System.in);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
			
			System.out.println("Enter contract data");
			System.out.print("Number: ");
			int number = sc.nextInt();
			System.out.print("Date (dd/MM/yyyy): "); 
			Date date = sdf.parse(sc.next());
			System.out.print("Contract value: ");
			double value = sc.nextDouble();
			
			Contract ct = new Contract(number, date, value);
			
			System.out.print("Enter number of installments: ");
			int numberContract = sc.nextInt();
						
			ContractService cs = new ContractService(new PaypalService());
			
			cs.processContract(ct, numberContract);
			
			System.out.println("Instalmments");
			for (Installment it : ct.getInstallment()) {
			System.out.println(it);
			}
			
			
			sc.close();
		}
}
