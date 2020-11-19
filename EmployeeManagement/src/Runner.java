import java.io.*;
import java.util.Scanner;
public class Runner{
	public static void main(String args[])throws IOException {
		EmployeeManagement ob=new EmployeeManagement();
		Scanner sc=new Scanner(System.in);
		System.out.println("--------------------------------------------------------------------------------------------------------------");
		System.out.println("                                                    Welcome  to  FSociety");
		System.out.println("--------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("How can we serve you today?");
		System.out.println("1. View details of an existing employee");
		System.out.println("2. Add a new Employee");
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Enter your choice :");
		  int ch=sc.nextInt();
		while(true) {
		 if(ch==1) {
			 System.out.println();
			 System.out.println("Enter the employee ID :");
			 String id=sc.next();
			 System.out.println();
			 System.out.println("Employee Details :");
			 EmployeeManagement.displayFromFile(id);
			 break;
		 }
		 else if(ch==2) {
			 System.out.println();
		  ob.getInformation();
		  System.out.println("Employee's Account is created, the details are :");
		  System.out.println();
		  ob.display();
		  break;
		}
		 else {
			 System.out.println("Invalid Choice, please enter again.");
			 ch=sc.nextInt();
		 }
	}
		
	}
}