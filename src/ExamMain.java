import java.io.IOException;
import java.util.Scanner;
public class ExamMain {
	static void startmenu() throws IOException
	{
		Login l;
		Scanner sc=new Scanner(System.in);
		int path=decidePath(sc);
		if(path==1)
		{
			l=new Login(sc);
			l.getdata();
		}
		else
		{
			Register r=new Register(sc);
			r.getentry();
			l=new Login(r);
		}
		menu(sc,l);
	}
	static void menu(Scanner sc,Login l) throws IOException
	{
		int x;
		TestResult t;
		TestAnalysis a;
		ViewSolutions v;
		System.out.println("What would you like to do :");
		System.out.println("1.Attempt a Test..\n2.View Analysis of subjects you have attempted..\n3.View Solutions..\n4.About you..\n5.Logout.\n\n\n");
		do{
			System.out.print("Enter your choice(1/2/3/4/5) :");
			x=sc.nextInt();
		}while(x>5||x<1);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		switch(x)
		{
		case 1:
			t=new TestResult(l,sc);
			t.getSubject();
			try
			{
				t.Testing();
			}catch(InterruptedException e)
			{
				System.out.println(e);
			}
			t.calculateResult();
			t.displayDetails();
			break;
		case 2:
			a=new TestAnalysis(l,sc);
			a.getSubject();
			a.setDetails();
			a.displayDetails();
			break;
		case 3:
			v=new ViewSolutions(l,sc);
			v.getSubject();
		case 4:
			l.displayDetails();
			break;
		case 5:
			ExamMain.startmenu();
		default:
		}
	}
	static int decidePath(Scanner sc)
	{
		
		String choice;
		System.out.println("*************************************************************************************\nWelcome to Online Examinations!!!\n*************************************************************************************\n\n");
		System.out.println("Signin? Havent Registered? ");
		do
		{
			System.out.println("Enter your choice (Signin/Register) :");
			choice=sc.next();
			if(choice.compareToIgnoreCase("signin")==0)
				return 1;
			else if(choice.compareToIgnoreCase("register")==0)
				return 2;
			else 
				System.out.println("Wrong Choice!!...");
		}
		while(true);
	}
	public static void main(String[] args)throws IOException
	{
		startmenu();
	}
}