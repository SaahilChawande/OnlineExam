import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
public class Certificate extends TestResult {
	String date;
	Certificate(Scanner sc,TestResult t)
	{
		super(sc);
		this.sc=sc;
		this.sub=t.sub;
		this.username=t.username;
		this.name=t.name;
		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		Date today=Calendar.getInstance().getTime();
		date=df.format(today);
	}
	Certificate(Scanner sc,Test t)
	{
		super(sc);
		this.sc=sc;
		this.sub=t.sub;
		this.username=t.username;
		this.name=t.name;
		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		Date today=Calendar.getInstance().getTime();
		date=df.format(today);
	}
	void createCertificate() throws IOException
	{
		int x;
		char c;
		File file=new File(username+sub+".txt");
		if(!file.exists())
		{
			file.createNewFile();
			File file2=new File("certificate_"+sub+".txt");
			if(!file2.exists())
			{
				file2.createNewFile();
				System.out.println("System Error!!!...");
			}
			else
			{
				FileWriter fr=new FileWriter(username+sub+".txt");
				FileInputStream fin=new FileInputStream("certificate_"+sub+".txt");
				BufferedReader br=new BufferedReader(new InputStreamReader(fin));
				while((x=br.read())!=-1)
				{
					c=(char)x;
					if(c=='~')
					{
						fr.write(name);
					}
					else if(c=='$')
					{
						fr.write(date);
					}
					else
					{
						fr.write(c);
					}
				}
				br.close();
				fr.close();
				System.out.println("Certificate created!!..");
			}
		}
		else
		{
			System.out.println("Certificate already exists!!..");
		}
	}
	void viewCertificate() throws IOException
	{
		File file=new File(username+sub+".txt");
		if(!file.exists())
		{
			System.out.println("Certificate doesn't Exist!!...");
		}
		else
		{
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			int x;
			FileInputStream fin=new FileInputStream(username+sub+".txt");
			BufferedReader br=new BufferedReader(new InputStreamReader(fin));
			while((x=br.read())!=-1)
			{
				System.out.print((char)x);
			}
			br.close();
			System.out.println("Press Enter to continue :");
			sc.nextLine();
			sc.nextLine();
		}
	}
}
