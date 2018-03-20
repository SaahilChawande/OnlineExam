import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Register {
	public Scanner sc;
	protected String name,username,password,contact;
	Register(Scanner sc)
	{
		this.sc=sc;
	}
	public static boolean isNumeric(String s)
	{
		char c[]=s.toCharArray();
		for(char x:c)
		{
			if(Character.isDigit(x)==false)
				return false;
		}
		return true;
	}
	public static boolean isAlphabetic(String s)
	{
		char c[]=s.toCharArray();
		for(char x:c)
		{
			if(Character.isLetter(x)==false)
				return false;
		}
		return true;
	}
	void getentry() throws IOException
	{
		int flag;
		String passwordconfirm;
		System.out.print("Enter Name :");
		sc.nextLine();
		do{
			name=sc.nextLine();
			name=name.trim();
			if(isAlphabetic(name.replaceAll("\\s+", "")))
				flag=1;
			else
			{
				flag=0;
				System.out.print("Enter a valid name :");
			}
		}while(flag==0);
		System.out.print("Enter Phone Number :");
		do{
			contact=sc.next();
			if(isNumeric(contact)&&contact.length()==10)
				flag=1;
			else
			{
				flag=0;
				System.out.print("Enter a valid phone number :");
			}
		}while(flag==0);
		File file=new File("Data.txt");
		if(!file.exists())
			file.createNewFile();
		String line=null;
		do
		{
			FileInputStream fin=new FileInputStream("Data.txt");
			BufferedReader br=new BufferedReader(new InputStreamReader(fin));
			System.out.print("Enter Username :");
			username=sc.next();
			int i;
			String u;
			while((line=br.readLine())!=null)                           //till end of file is not reached. 
			{
				i=line.indexOf('*');
				u=line.substring(0,i);
				if(username.compareTo(u)==0)	  //p is extracted from file.
				{
					flag=0;
					break;
				}
			}
			if(flag==1)
				break;
			else
			{
				flag=1;
				System.out.println("Username already exists!!");
			}
			br.close();
			fin.close();
		}while(true);
		do
		{
			System.out.print("Enter Password :");
			password=sc.next();
			System.out.print("Confirm password :");
			passwordconfirm=sc.next();
			if (password.compareTo(passwordconfirm)!=0)
				System.out.println("Password doesnt match!");
			else if(password.length()<8)
				System.out.println("Password is too small!");
			else
				break;
		}while(true);
		addentry();
	}
	void addentry()throws IOException//Writing to file
	{
		FileWriter fw=new FileWriter("Data.txt",true);
		fw.write(username+"*"+password+"~"+name+"\t"+contact+"\n");
		fw.close();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Registered Successfully!!!!");
		System.out.println("Press Enter to continue :");
		sc.nextLine();
		sc.nextLine();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
}
