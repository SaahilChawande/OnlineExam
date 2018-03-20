import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Login {
	public Scanner sc;
	protected String username,name,contact,questioncode;
	private String password;
	Login(Register r)
	{
		this.username=r.username;
		this.name=r.name;
		this.contact=r.contact;
		this.password=r.password;
		this.sc=r.sc;
	}
	Login(Scanner sc)
	{
		this.sc=sc;
	}
	void getdata() throws IOException
	{
		System.out.print("Enter Username :");
		username=sc.next();
		System.out.print("Enter Password :");
		password=sc.next();
		checkvalidity();
	}
	private void checkvalidity() throws IOException
	{
		int flag=0;
		File file=new File("Data.txt");
		if(!file.exists())
			file.createNewFile();
		FileInputStream fin=new FileInputStream("Data.txt");			
		BufferedReader br=new BufferedReader(new InputStreamReader(fin));
		String u,p,l;
		while((l=br.readLine())!=null)                
		{
			u=l.substring(0,l.indexOf('*'));
			p=l.substring(l.indexOf('*')+1,l.indexOf('~'));
			if((u.compareTo(username)==0)&&(p.compareTo(password)==0))  																								
			{                                                           
				flag=1;
				break;
			}
			
		}
		if (flag==1)
		{
			name=l.substring(l.indexOf('~')+1,l.indexOf('\t'));
			contact=l.substring(l.indexOf('\t')+1);
			br.close();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Username and Password accepted..");
			System.out.println("Welcome "+name+"!!");
		}
		else
		{
			System.out.println("Wrong Username or Password..");
			br.close();
				getdata();
		}
		
	}
	void displayDetails() throws IOException
	{
		String choice;
		System.out.println("Name :"+name);
		System.out.println("Contact :"+contact);
		System.out.println("Username :"+username);
		do{
			System.out.println("Would you like to return to Home?(Yes/No) :");
			choice=sc.next();
			if(choice.compareToIgnoreCase("yes")==0)
				ExamMain.menu(sc,this);
			else if(choice.compareToIgnoreCase("no")==0)
				System.exit(0);
			else
			{
				choice=null;
				System.out.println("Wrong choice!!!");
			}
		}while(choice==null);	
	}
}
