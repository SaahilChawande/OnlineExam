import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class TestResult extends Test {
	double tmarks,omarks;
	int right,wrong,unattempted;
	TestResult(Scanner sc)
	{
		super(sc);
	}
	TestResult(Login l,Scanner sc)
	{
		super(l,sc);
		this.sc=sc;
		omarks=0;
		tmarks=80;
	}
	void calculateResult() throws IOException
	{
		String l;
		File file=new File(sub+qc+"ans.txt");
		if(!file.exists())
			file.createNewFile();
		FileInputStream fin=new FileInputStream(sub+qc+"ans.txt");
		BufferedReader br=new BufferedReader(new InputStreamReader(fin));
		int i=0;
		while(i<20&&(l=br.readLine())!=null)
		{
			if(l.charAt(0)=='~')
			{
				if(answers[i][0]=='-')
				{
					unattempted++;
					answers[i][2]=l.charAt(11);
					answers[i++][1]=0;
				}
				else if(l.charAt(11)==answers[i][0])
				{
					omarks+=4;
					right++;
					answers[i][2]=l.charAt(11);
					answers[i++][1]=4;
				}
				else
				{
					omarks-=1;
					wrong++;
					answers[i][2]=l.charAt(11);
					answers[i++][1]=-1;
				}
			}
		}
		br.close();
	}
	void displayDetails() throws IOException
	{
		String choice;
		System.out.println("Following are the results of the test attempted :");
		System.out.println("Subject Name :"+sub);
		System.out.println("Attempted :"+(20-unattempted));
		System.out.println("Correct :"+right);
		System.out.println("Incorrect :"+wrong);
		System.out.println("Percentage :"+(omarks/tmarks*100)+"%");
		System.out.println("Following are the correct answers of the test attempted :\nQ.No.  Correct Answer  Your Answer  Marks Rewarded");
		for(int i=0;i<20;i++)
		{
			System.out.format(" %d\t  %c\t\t%c\t\t  %2d\n",(i+1),(char)answers[i][2],(char)answers[i][0],answers[i][1]);
		}
		storeResults();
		viewSolutions();
		if(qc==3)
		{
			Certificate c=new Certificate(sc,this);
			System.out.println("Course Completed!!!..");
			c.createCertificate();
			System.out.println("View Certificate?");
			do{
				System.out.println("Enter your choice(Yes/No) :");
				choice=sc.next();
				if(choice.compareToIgnoreCase("yes")==0)
					c.viewCertificate();
				else if(choice.compareToIgnoreCase("no")==0)
					;
				else
				{
					choice=null;
					System.out.println("Wrong choice!!!");
				}
			}while(choice==null);
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
	void storeResults() throws IOException
	{
		File file=new File(username+".txt");
		if(!file.exists())
		{
			file.createNewFile();
		}
		FileWriter fw=new FileWriter(username+".txt",true);
		fw.write("$\n");
		fw.write("Subject Name :"+sub+"\n");
		fw.write("Question Paper Number :"+qc+"\n");
		fw.write("Attempted :"+(20-unattempted)+"\n");
		fw.write("Correct :"+right+"\n");
		fw.write("Incorrect :"+wrong+"\n");
		fw.write("Percentage :"+(omarks/tmarks*100)+"%"+"\n");
		fw.write("~\n");
		fw.close();
	}
	void viewSolutions() throws IOException
	{
		String choice;
		do{
			System.out.println("View Solutions?(Yes/No) :");
			choice=sc.next();
			if(choice.compareToIgnoreCase("yes")==0)
			{
				ViewSolutions v=new ViewSolutions(this,sc);
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				v.displaySolutions();		
			}
			else if(choice.compareToIgnoreCase("no")==0)
			{
				return;
			}
			else
			{
				System.out.println("Wrong choice!!..");
				choice=null;
			}
		}
		while(choice==null);
	}
}