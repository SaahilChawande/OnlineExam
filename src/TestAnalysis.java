import java.io.BufferedReader;         //https://drive.google.com/open?id=0B-8kkUnoA6f3Y05IeEF1bklpQjg
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class TestAnalysis extends Login {
	private int testsgiven,tcorrect,tquestions,tattempted;
	private double overallpercentage;
	private String sub;
	TestAnalysis(Login l,Scanner sc)
	{
		super(sc);
		this.sc=sc;
		this.username=l.username;
		this.name=l.name;
		this.contact=l.contact;
	}
	void getSubject()
	{
		System.out.println("Enter the subject to be analysed(Java/C++/Python/JavaScript) :");
		int flag=0;
		do{
			sub=sc.next();
			if(sub.compareToIgnoreCase("java")==0||sub.compareToIgnoreCase("c++")==0||sub.compareToIgnoreCase("python")==0||sub.compareToIgnoreCase("javascript")==0)
				flag=1;
			if(flag==0)
				System.out.println("Wrong choice..Please enter one of the given Subjects :");
		}while(flag==0);
		sub=sub.toUpperCase();
		System.out.println("\n\n\n\n\n\n\n\n\n\n");
		
	}
	void setDetails() throws IOException
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
					l1=br.readLine();
					tattempted+=Integer.parseInt(l1.substring(11));
					l1=br.readLine();
					tcorrect+=Integer.parseInt(l1.substring(9));
				}
			}
		}
		tquestions=testsgiven*20;
		overallpercentage=(((double)(tcorrect*5-tattempted))/(tquestions*4))*100;
		br.close();
	}
	void displayDetails() throws IOException
	{
		String choice;
		if(testsgiven==0)
		{
			System.out.println("You haven't attempted this subject !!");
		}
		else
		{
			System.out.println("Following is the Analysis of the subject :");
			System.out.println("Subject Name :"+sub);
			System.out.println("Question Papers attempted :"+testsgiven);
			System.out.println("Attempted :"+tattempted);
			System.out.println("Correct :"+tcorrect);
			System.out.println("Incorrect :"+(tattempted-tcorrect));
			System.out.println("Percentage :"+overallpercentage+"%");
		}
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
