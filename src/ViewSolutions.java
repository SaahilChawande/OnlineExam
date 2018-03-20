import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class ViewSolutions extends Login {
	ViewSolutions(Login l,Scanner sc)
	{
		super(sc);
		this.sc=sc;
		this.username=l.username;
		this.name=l.name;
		this.contact=l.contact;
	}
	ViewSolutions(TestResult t,Scanner sc)
	{
		super(sc);
		this.sc=sc;
		this.sub=t.sub;
		this.username=t.username;
		this.name=t.name;
		this.contact=t.contact;
		this.qc=t.qc;
	}
	String sub;
	int testsgiven;
	int qc;
	void getSubject() throws IOException
	{
		File file=new File(username+".txt");
		if(!file.exists())
			file.createNewFile();
		FileInputStream fin=new FileInputStream(username+".txt");
		BufferedReader br=new BufferedReader(new InputStreamReader(fin));
		if(br.read()==-1)
		{
			System.out.println("\n\n\n\n\n\n\n\n\n\n");
			System.out.println("You have attempted no tests!!...\nSolutions can be viewed only for attempted tests...\nReturning home...");
			br.close();
			ExamMain.menu(sc,this);
		}
		else
		{
			br.close();
			int flag=0;
			String choice;
			System.out.println("Solutions can be viewed only for attempted tests...\nSelect subject :\n1.Java\n2.C++\n3.Python\n4.JavaScript\n\n\n");
			System.out.print("Enter your choice :");
			do{
				sub=sc.next();
				if(sub.compareToIgnoreCase("java")==0||sub.compareToIgnoreCase("c++")==0||sub.compareToIgnoreCase("python")==0||sub.compareToIgnoreCase("javascript")==0)
					flag=1;
				if(flag==0)
					System.out.println("Wrong choice..Please enter one of the given Subjects :");
			}while(flag==0);
			sub=sub.toUpperCase();
			settestsgiven();
			getqc();
			do{
				System.out.println("Would you like to return to Home?(Yes/No) :");
				choice=sc.next();
				if(choice.compareToIgnoreCase("yes")==0)
				{
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					ExamMain.menu(sc,this);
				}
				else if(choice.compareToIgnoreCase("no")==0)
				{
					System.exit(0);
				}
				else
				{
					choice=null;
					System.out.println("Wrong choice!!!");
				}
			}while(choice==null);
		}
	}
	void settestsgiven() throws IOException
	{
		String l1;
		File file=new File(username+".txt");
		if(!file.exists())
			file.createNewFile();
		FileInputStream fin=new FileInputStream(username+".txt");
		BufferedReader br=new BufferedReader(new InputStreamReader(fin));
		while((l1=br.readLine())!=null)
		{
			if(l1.charAt(0)=='$')
			{
				l1=br.readLine();
				if(l1.substring(14).compareTo(sub)==0)
				{
					l1=br.readLine();
					testsgiven=Integer.parseInt(l1.substring(23));
				}
			}
		}
		br.close();
	}
	void getqc() throws IOException
	{
		if(testsgiven==0)
		{
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("You havent attempted this subject!!..");
			getSubject();
		}
		else
		{
			System.out.println("Enter Question Paper Code(1/2/3) :");
			qc=sc.nextInt();
			if(testsgiven<qc)
			{
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				System.out.println("Cannot view solutions of unattempted test!!.");
				getqc();
			}
		}
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		displaySolutions();
	}
	void displaySolutions() throws IOException
	{
		char c;
		int x;
		File file1=new File(sub+qc+".txt");
		if(!file1.exists())
			file1.createNewFile();
		File file2=new File(sub+qc+"ans.txt");
		if(!file2.exists())
			file2.createNewFile();
		FileInputStream fin1=new FileInputStream(sub+qc+".txt");
		FileInputStream fin2=new FileInputStream(sub+qc+"ans.txt");
		BufferedReader br1=new BufferedReader(new InputStreamReader(fin1));
		BufferedReader br2=new BufferedReader(new InputStreamReader(fin2));
		br2.read();
		sc.nextLine();
		while((x=br1.read())!=-1)
		{
			c=(char)x;
			if(c!='$')
			{
				System.out.print(c);
			}
			else
			{
				while((x=br2.read())!=-1&&(c=(char)x)!='~')
				{
					System.out.print(c);
				}
				System.out.println("\nPress Enter to continue :");
				sc.nextLine();
			}
		}
		br1.close();
		br2.close();
	}
}
