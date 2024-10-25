package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.service.BrazilTaxService;
import model.service.RentalService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern(("dd/MM/yyyy HH:mm"));
		
		System.out.println("Enter rental details");
		System.out.print("car model: ");
		String carModel = sc.nextLine();
		System.out.print("Withdrawal (dd/MM/yyyy HH:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.print("Return (dd/MM/yyyy HH:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));

		System.out.print("Enter the price per hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Enter the price per day: ");
		double pricePerDay = sc.nextDouble();	
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		
		rentalService.processInvoice(cr);
		
		System.out.println("Invoice: ");
		System.out.println("Basic payment: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Tax: " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Full payment: " + String.format("%.2f", cr.getInvoice().getTotalpayment()));
		
		sc.close();
	}

}
