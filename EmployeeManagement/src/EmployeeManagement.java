import java.util.Scanner;
import java.lang.Math;
import java.io.*;

public class EmployeeManagement{
	
	private String firstName;
	private String lastName;
	private String department;
	private String employeeID;
	private String emailID;
	private String phoneNumber;
	private String alternateEmailID;
	private String password;
	
	private static Scanner sc=new Scanner(System.in);
	private static File obj=new File("credentials.txt");
	private static FileWriter fw;
	private static FileReader fr;
	
	public void getInformation() throws IOException{
		
		obj.createNewFile();
		fw=new FileWriter("credentials.txt",true);
		System.out.println("Enter Employee name :");
		firstName=sc.next();
		lastName=sc.next();
		System.out.println();
		department=getDepartment();
		employeeID=getEmployeeID();
		emailID=generateEmailID();
		System.out.println();
		System.out.println("Enter Employee's contact number :");
		phoneNumber=sc.next();
        while(phoneNumber.length()!=10) {
        	System.out.println("Invalid phone number,please enter again :");
        	phoneNumber=sc.next();
        }
        System.out.println();
		System.out.println("Enter Employee's personal email id :");
		alternateEmailID=sc.next();
		while(!isValidEmail()) {
        	System.out.println("Invalid email id,please enter again :");
        	alternateEmailID=sc.next();
        }
		password=generatePassword();
		System.out.println("--------------------------------------------------------------------------------------------------------------");
		System.out.println();
		fw.write("\n"+employeeID+" "+firstName+" "+lastName+" "+department+" "+emailID+" "+password+" "+alternateEmailID+" "+phoneNumber);
		fw.close();
	}
	
	private String getDepartment() {
		System.out.println("Please choose Employee's department :");
		System.out.println();
		System.out.println("Press 1 for Accounts and Finance department");
		System.out.println("Press 2 for Research and Development department");
		System.out.println("Press 3 for Human Resource Management department");
		System.out.println("Press 4 for Marketing department");
		System.out.println("Press 5 for Production department");
		System.out.println("Press 6 for Sales department");
		System.out.println();
		System.out.println("Enter your choice :");
		int ch=sc.nextInt();
		while(true) {
		   if(ch==1) 
			  return "accounts";
		   else if(ch==2)
			  return "dev";
		   else if(ch==3)
			  return "hr";
		   else if(ch==4)
			  return "marketing";
		   else if(ch==5)
			  return "production";
		   else if(ch==6)
			  return "sales";
		   else {
			   System.out.println("Wrong choice, please enter again :");
			   ch=sc.nextInt();
		   }
		} //end of while
	} //end of getDepartment()
	
	private String getEmployeeID() {
		String str="0123456789";
		String id="";
		for(int i=3;i<=10;i++){
			id+=str.charAt((int)(Math.random()*str.length()));
		}
		return id;
	}
	private String generateEmailID() {
		return (char)(firstName.charAt(0)+32)+firstName.substring(1)+"."+department+"@fsociety.com";
	}
	
	private String generatePassword() {
		String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@!$.";
		String pass="";
		while(!isValidPassword(pass)) {
			pass="";
			int n=8+(int)Math.random()*12;
			for(int i=1;i<=n;i++) {
				pass+=str.charAt((int)(Math.random()*str.length()));
			}
		}
		return pass;
	}
	
	private boolean isValidPassword(String pass) {
		//password must be 8 characters long
		if(pass.length()<8)
			return false;
		boolean hasCapital=false,hasSmall=false,hasSpecial=false,hasDigit=false;
		//password must have at least one Upper Case, one Lower Case, one Digit, one Special Character
		for(int i=0;i<pass.length();i++) {
			if(65<=pass.charAt(i) && pass.charAt(i)<=90)
				hasCapital=true;
			if(97<=pass.charAt(i) && pass.charAt(i)<=122)
				hasSmall=true;
			if(48<=pass.charAt(i) && pass.charAt(i)<=57)
				hasDigit=true;
			if(pass.charAt(i)=='@' || pass.charAt(i)=='!' || pass.charAt(i)=='$' || pass.charAt(i)=='.')
				hasSpecial=true;
		}
		return hasCapital&&hasSmall&&hasSpecial&&hasDigit;
	}
	
	private boolean isValidEmail() {
		if(alternateEmailID.contains(" ") || !alternateEmailID.contains("@") || !alternateEmailID.contains(".") || alternateEmailID.charAt(0)=='@' || alternateEmailID.charAt(0)=='.' || alternateEmailID.charAt(alternateEmailID.length()-1)=='@' || alternateEmailID.charAt(alternateEmailID.length()-1)=='.')
			return false;
		return true;
	}
	public void display()throws IOException {
		
		fw=new FileWriter("credentials.txt",true);
		System.out.println("Name = "+firstName+" "+lastName);
		System.out.print("Department = ");
		if(department.equals("accounts"))
			System.out.println("Accounts and Finance");
		else if(department.equals("dev"))
			System.out.println("Research and Development");
		else if(department.equals("hr"))
			System.out.println("Human Resource Management");
		else if(department.equals("marketing"))
			System.out.println("Marketing");
		else if(department.equals("production"))
			System.out.println("Production");
		else
			System.out.println("Sales");

		System.out.println("Employee ID = "+employeeID);
		System.out.println("Company Email ID = "+emailID);
		System.out.println("Password = "+password);
		System.out.println("Alternate Email ID = "+alternateEmailID);
		System.out.println("Contact Number = "+phoneNumber);
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Do you wish to change Employee's passowrd?(Press \"Y\" for yes and \"N\" for no)");
		char ch;
		ch=sc.next().charAt(0);
		if(ch=='Y' || ch=='y') {
			System.out.println();
			System.out.println("Remember, the password must have an uppercase letter,a lowercase letter,a digit and a special character like \'@\', \'!\', \'$\', \'.\' and must be atleast 8 characters in length");
			System.out.println("Enter the password : ");
			String pass=sc.next();
			while(!isValidPassword(pass)) {
				System.out.println("Password id not valid, enter again :");
				pass=sc.next();
			}
		    fw.write(" "+pass);
			password=pass;
			System.out.println();
			System.out.println("Employee's new credentials are :");
			System.out.println();
			System.out.println("Email ID = "+emailID);
			System.out.println("Password = "+password);
			fw.close();
		}
	    System.out.println();
		System.out.println("Employee Added Succesfully");

	}
	
	public static void displayFromFile(String id)throws IOException {
		fr=new FileReader("credentials.txt");
		BufferedReader br=new BufferedReader(fr);
		String[] words;
		String s;
		int flag=0;
		System.out.println();
		while((s=br.readLine())!=null) {
			words=s.split(" ");
			if(words[0].equals(id)) {
				flag=1;
			for(int i=0;i<8;i++) {
				if(i==0)
				   System.out.println("Employee ID = "+words[i]);
				if(i==1) {
					System.out.println("Employee Name = "+words[i]+" "+words[i+1]);
					i++;
				}
				if(i==3) {
					System.out.print("Department = ");
					if(words[i].equals("accounts"))
						System.out.println("Accounts and Finance");
					else if(words[i].equals("dev"))
						System.out.println("Research and Development");
					else if(words[i].equals("hr"))
						System.out.println("Human Resource Management");
					else if(words[i].equals("marketing"))
						System.out.println("Marketing");
					else if(words[i].equals("production"))
						System.out.println("Production");
					else
						System.out.println("Sales");
				}
				if(i==4)
					System.out.println("Email ID = "+words[i]);
				if(i==5 && words.length==8)
					System.out.println("Password = "+words[i]);
				if(i==5 && words.length==9)
					System.out.println("Password = "+words[8]);
				if(i==6)
					System.out.println("Alternate Email ID = "+words[i]);
				if(i==7)
					System.out.println("Contact Number = "+words[i]);
			}
			break;
		  }
		}
		if(flag==0)
			System.out.println("Record not found.");
	}
}
