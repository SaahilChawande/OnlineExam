import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Test extends Login {
	protected String sub;
	protected int answers[][]=new int[20][3],qc;
	Test(Scanner sc)
	{
		super(sc);
	}
	Test(Login l,Scanner sc)
	{
		super(sc);
		this.sc=sc;
		this.username=l.username;
		this.name=l.name;
		this.contact=l.contact;
		qc=1;
	}
	void getSubject() throws IOException
	{
		int flag=0;
		System.out.println("The following tests are available :\n1.Java\n2.C++\n3.Python\n4.JavaScript\n\n\n");
		System.out.print("Enter your choice :");
		do{
			sub=sc.next();
			if(sub.compareToIgnoreCase("java")==0||sub.compareToIgnoreCase("c++")==0||sub.compareToIgnoreCase("python")==0||sub.compareToIgnoreCase("javascript")==0)
				flag=1;
			if(flag==0)
				System.out.println("Wrong choice..Please enter one of the given Subjects :");
		}while(flag==0);
		sub=sub.toUpperCase();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		setqc();
		if(qc==4)
		{
			String choice;
			System.out.println("Course Completed !!!");
			System.out.println("View Certificate?");
			do{
				System.out.println("Enter your choice(Yes/No) :");
				choice=sc.next();
				if(choice.compareToIgnoreCase("yes")==0)
				{
					Certificate c=new Certificate(sc,this);
					c.viewCertificate();
				}
				else if(choice.compareToIgnoreCase("no")==0)
					;
				else
				{
					choice=null;
					System.out.println("Wrong choice!!!");
				}
			}while(choice==null);
			System.out.println("Would you like to attempt other subject?");
			do{
				System.out.println("Enter your choice(Yes/No) :");
				choice=sc.next();
				if(choice.compareToIgnoreCase("yes")==0)
				{
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					getSubject();
				}
				else if(choice.compareToIgnoreCase("no")==0)
					ExamMain.menu(sc,this);
				else
				{
					choice=null;
					System.out.println("Wrong choice!!!");
				}
			}while(choice==null);
		}
	}
	void setqc() throws IOException
	{
		String l1;
		System.out.println(sub);
		File file=new File(username+".txt");
		if(!file.exists())
			file.createNewFile();
		FileInputStream fin=new FileInputStream(username+".txt");
		qc=1;
		BufferedReader br=new BufferedReader(new InputStreamReader(fin));
		while((l1=br.readLine())!=null)
		{
			if(l1.charAt(0)=='$')
			{
				l1=br.readLine();
				if(l1.substring(14).compareTo(sub)==0)
				{
					l1=br.readLine();
					qc=Integer.parseInt(l1.substring(23))+1;
				}
			}
		}
		br.close();
	}
	void Testing() throws IOException,InterruptedException
	{
		int x;
		char c;
		int i=0;
		File f=new File(sub+qc+".txt");
		System.out.println("Subject :"+sub+"\nQuestion Paper Code :"+qc);
		System.out.println("You have 30 seconds to attempt each question...\nPress Enter to start the test...");
		sc.nextLine();
		sc.nextLine();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		if(!f.exists())
			f.createNewFile();
		FileInputStream fin=new FileInputStream(sub+qc+".txt");
		BufferedReader br=new BufferedReader(new InputStreamReader(fin));
		while((x=br.read())!=-1)
		{
			c=(char)x;
			if(c!='$')
			{
				System.out.print(c);
			}
			else
			{
				System.out.print("\nEnter your choice(a/b/c/d/-) :");
				ConsoleInput con=new ConsoleInput();
				String input=con.readLine();
				if(input==null||input.length()==0)
				answers[i++][0]="-".toLowerCase().charAt(0);
				else
				answers[i++][0]=input.toLowerCase().charAt(0);
				System.out.println("\n");
			}
		}
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Test ends !!!");
		br.close();
		System.out.println("Press Enter to continue :");
		sc.nextLine();
		sc.nextLine();
	}
}