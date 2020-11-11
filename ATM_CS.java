package com.greeshmitha;
import java.util.*;
interface display{
   abstract int functioninvoker();
	//abstract void displayMenu();
}
//class for account details
class Useraccount{
	private int accountnum;
	private int pin;
	private double balance=0;
	ArrayList <Pair>pl=new ArrayList<>();
	public int getaccountnum() {
		return this.accountnum;
	}
	public void setaccountnum(int num) {
	   this.accountnum=num;
	}
	public int getpin() {
		return this.pin;
	}
	public void setpin(int pinnum) {
		this.pin=pinnum;
	}
	public void credit(double amount) {
		this.balance+=amount;
	}
	public void debit(double amount) {
		this.balance-=amount;
	}
	public double getaccbalance() {
		return this.balance;
	}
	public boolean ispinvalid(int pin) {
		if(this.pin==pin)
			return true;
		else
			return false;
	}
	
}
//Taken account numbers from 00000 to 50000 and for pin numbers add+1 to account number
class Database{
	Scanner sc=new Scanner(System.in);
    ArrayList<Useraccount>data=new ArrayList<>();
	Database(){
		Useraccount user=new Useraccount();
		for(int i=0;i<50001;i++) {
			user.setaccountnum(i);
			user.setpin(i+1);
			data.add(user);
			user=new Useraccount();
		}
	}
	private Useraccount searchaccount(int num) {
		for(int i=0;i<data.size();i++) {
			if(data.get(i).getaccountnum()==num)
				return data.get(i);
		}
		return null;
	}
	public void credit(int num,double amount) {
		if(num<data.size())
		data.get(num).credit(amount);
		else
			System.out.println("User is not valid");
	}
	public void debit(int num,double amount) {
		if(num<data.size())
			data.get(num).debit(amount);
			else
				System.out.println("User is not valid");
	}
	public double getbalance(int num) {
		if(num<data.size())
			return data.get(num).getaccbalance();
			else {
				System.out.println("User is not valid");
				return 0;
			}
	}
	public boolean isuservalid(int num,int pin) {
		if(searchaccount(num)!=null) {
			return searchaccount(num).ispinvalid(pin);
		}
		else
			return false;
	}
	public void changepin(int num,int pin_no) {
		if(searchaccount(num).ispinvalid(pin_no)) {
			System.out.println("Enter pin number that you want to change:");
			int pin_num=sc.nextInt();
			searchaccount(num).setpin(pin_num);
			System.out.println("Succesfully changed");
			//return searchaccount(num).getpin();
		}
		else {
			System.out.println("Invalid credentials..please try again");
			//return 0;
		}
	}
	
}
class Balanceinquiry{
	private int accnum;
	private Database data;
	public Balanceinquiry(int num,Database bankdata) {
		this.accnum=num;
		this.data=bankdata;
	}
	void execute() {
		double balance=data.getbalance(accnum);
		System.out.println("Your account balance is:");
		System.out.println(balance);
	}
}
//intially we are takin bank is loaded with rp.100-1000 notes and rp.200-500 notes and rp.500-1000 notes and rp.2000-400 notes
class CashDispenser{
	Scanner sc=new Scanner(System.in);
	private  static final int COUNT1=1000;
	private static final int COUNT2=500;
	private static final int COUNT3=1000;
	private static final int COUNT4=400;
	private int cnt1,cnt2,cnt3,cnt4;
	CashDispenser(){
		cnt1=COUNT1;
		cnt2=COUNT2;
		cnt3=COUNT3;
		cnt4=COUNT4;
	}
	public int functioninvoke() {
		System.out.println("Choose the denomination of notes 1.100 2.200 3.500 4.2000");
		System.out.println("PRESS (1/2/3/4)");
		int opt=sc.nextInt();
		return opt;	
	}
	public void setcashlimit() {
		int option=functioninvoke();
		if(option==1) {
			System.out.println("Set the notes limit to be dipensed:(enter b/w 0-1000)");
			int opt=sc.nextInt();
			cnt1=opt;
			System.out.println("Cash limit set to"+cnt1);
		}
		if(option==2) {
			System.out.println("Set the notes limit to be dipensed:(enter b/w 0-500)");
			int opt=sc.nextInt();
			cnt2=opt;
			System.out.println("Cash limit set to"+cnt2);
		}
		if(option==3) {
			System.out.println("Set the notes limit to be dipensed:(enter b/w 0-1000)");
			int opt=sc.nextInt();
			cnt3=opt;
			System.out.println("Cash limit set to"+cnt3);
		}
		if(option==4) {
			System.out.println("Set the notes limit to be dipensed:(enter b/w 0-400)");
			int opt=sc.nextInt();
			cnt4=opt;
			System.out.println("Cash limit set to"+cnt4);
		}
	}
	public boolean iscashavailable(int amount) {
		int option=functioninvoke();
		int notesrequired=0;
		boolean yn=false;
		switch(option) {
		case 1:
			notesrequired=amount/100;
			if(cnt1>=notesrequired)
				yn=true;
			break;
		case 2:
			notesrequired=amount/200;
			if(cnt2>=notesrequired)
				yn=true;
			
			break;
		case 3:
			notesrequired=amount/500;
			if(cnt3>=notesrequired)
				yn=true;
			break;
		case 4:
			notesrequired=amount/2000;	
			if(cnt4>=notesrequired)
				yn=true;
			break;
		}
		return yn;	
	}
	public void cashdispensed(int amount) {
		int option=functioninvoke();
        int count1=0,count2=0,count3=0,count4=0;
		if(option==1) {
	    count1=amount/100;
	   cnt1-=count1;
		}
		if(option==2) {
		    count2=amount/200;
		   cnt2-=count2;
			}
		if(option==3) {
		    count3=amount/500;
		   cnt3-=count3;
			}
		if(option==4) {
		    count4=amount/2000;
		   cnt4-=count4;
			}
	}
}
class Withdrawal{
	Scanner sc=new Scanner(System.in);
	private double balance;
	private int accnum;
	private Database data;
	private CashDispenser cd;
	private Admin ad=new Admin();
	Withdrawal(int num,Database bankdata,CashDispenser c){
		this.accnum=num;
		this.data=bankdata;
		this.cd=c;
	}
	public void execute() {
		boolean cashdispensed=false;
		while(!cashdispensed) {
			System.out.println("Choose your withdrawal amount:");
			int amount=sc.nextInt();
			int opt;
			System.out.println("Enter 0 if you want to cancel withdrwal OR Enter anynumber to continue:");
			opt=sc.nextInt();
			if(opt!=0) {
				balance=data.getbalance(accnum);
				if(amount<=balance && ad.getmaxtransaction()>=amount) {
					if(cd.iscashavailable(amount)) {
						data.debit(accnum, amount);
						cashdispensed=true;
						cd.cashdispensed(amount);
						Pair pair=new Pair();
						pair.amount=amount;
	                    pair.opern="withdrawed";
						data.data.get(accnum).pl.add(pair);
						System.out.println("Cash will be dispensed now..Please collect it..");
					}
					else
						System.out.println("Cash is not available in atm");
				}
				else
					System.out.println("Cash is not available in your account..");
			}
			else {
				System.out.println("Cancelling transaction..");
				break;
			}	
		}
	}
}
class Deposit{
	Scanner sc=new Scanner(System.in);
	private int accnum;
	private Database data;
	private Admin ad=new Admin();
	Deposit(int num,Database bankdata){
		this.accnum=num;
		this.data=bankdata;
	}
	public void execute() {
	int amount;
	System.out.println("Enter ur amount OR enter 0 if you want to cancel deposit:");
	//System.out.println("If you want to deposit in your account(ENTER 1) else (ENTER 2)");
	amount=sc.nextInt();
	System.out.println("If you want to deposit in your account(ENTER 1) else (ENTER 2)");
	int opt=sc.nextInt();
    if(amount!=0 && ad.getmaxtransaction()>=amount) {
    	if(opt==1) {
    	System.out.println("Please insert the envolope having amount "+ amount+".");
    	data.credit(accnum, amount);
    	System.out.println("Your envolope has been received");
    	Pair pair=new Pair();
		pair.amount=amount;
        pair.opern="deposit";
		data.data.get(accnum).pl.add(pair);
    	}
    	if(opt==2) {
    		System.out.println("Enter the account number you want to deposit your amount:");
    		int acc=sc.nextInt();
    		System.out.println("Please insert the envolope having amount "+ amount+".");
    		data.credit(acc, amount);
    		System.out.println("Your envolope has been received");
    		Pair pair=new Pair();
    		pair.amount=amount;
            pair.opern="deposited";
    		data.data.get(accnum).pl.add(pair);
    		
    	}
      }
    else {
    	System.out.println("Cancelling Transaction..");
      }
   }
}
class Pair{
	int account_no,amount;
	String opern;
	
}
class Admin {
	Scanner sc=new Scanner(System.in);
	private int max_trans;
	int pw;
	Admin(){
		pw=1234;
		max_trans=50000;
	}
	public boolean ispwvalid(int password) {
		boolean yn=false;
		if(password==this.pw)
			yn=true;
		return yn;
	}
	public int functioninvoker() {
		System.out.println("WELCOME!!!");
		String fun[]=new String[] {"Change cashlimit","View account details","Set Maxtransaction","Ministatement"};
		System.out.println("Choose options:");
		for(int i=0;i<fun.length;i++) {
			System.out.println(i+1+" "+fun[i]);
		}
		System.out.println("PRESS (1/2/3/4)");
		int opt=sc.nextInt();
		return opt;
	}
	public int setmaxtransaction() {
		System.out.println("Enter maximum transaction amount:");
		int trans=sc.nextInt();
		return trans;
	}
	public int getmaxtransaction() {
		return this.max_trans;
	}
	public void displaymenu(CashDispenser c,Database d) {
		int opt=functioninvoker();
		switch(opt) {
		case 1:
			//CashDispenser c=new CashDispenser();
			c.setcashlimit();
			break;
		case 2:
			System.out.println("Enter account number:");
			int num=sc.nextInt();
			 double bal=d.data.get(num).getaccbalance();
			 System.out.println("Money in account number"+num+" is "+bal);
			 break;
		case 3:
			max_trans=setmaxtransaction();
			System.out.println("Max transaction is set to"+max_trans);
			break;
		case 4:
			System.out.println("Enter account number:");
			int num1=sc.nextInt();
			for(int i=0;i<d.data.get(num1).pl.size();i++) {
				   System.out.println("This amount "+d.data.get(num1).pl.get(i).amount+"  has been "+d.data.get(num1).pl.get(i).opern);
				   }
			
			
		}
	}
	
	
	 
}
class ATM implements display{
	   Scanner sc=new Scanner(System.in);
	   boolean isUservalid=false;
	   CashDispenser cash=new CashDispenser();
	   Database data=new Database();
	   int present_pin=0;
	   int present_acc=0;
	private boolean isuserauthenticated=false;
	  public int functioninvoker() {
		  String[]fun=new String[] {"admin","customer"};
		  for(int i=0;i<fun.length;i++) {
			  System.out.println(i+1+" "+fun[i]);
		  }
		  System.out.println("Press key(1/2):");
		  int opt=sc.nextInt();
		  return opt;
	  }
	  public void displayMenu() {
	while(true) {
		  int option=functioninvoker();
		  System.out.println("ENTER 0 if you want to exit");
		 if(option==1) {
			  System.out.println("Enter password:");
			  int opt=sc.nextInt();
			  Admin ad=new Admin();
			  if(ad.ispwvalid(opt)) {
				  ad.displaymenu(cash,data);
			  }
			  else {
				  System.out.println("Invalid password..Try again..");
			  }
		 }
			  if(option==2) {
				  while(true) {
					   while(!isuserauthenticated) {
						   System.out.println("WELCOME!!!");
						   System.out.println("Enter ur account number:");
						   int acc=sc.nextInt();
						   System.out.println("Enter pin number:");
						   int pin=sc.nextInt();
						   isuserauthenticated=data.isuservalid(acc, pin);
						   if(isuserauthenticated) {
							   present_acc=acc;
							   present_pin=pin;
						   }
						   else {
							   System.out.println("Invalid credentials!!..Please try again..");
						   }  
					   }
					   System.out.println("WELCOME!!!");
					   String[]func=new String[] {"Balanceinquiry","Withdrawal","Deposit","Change pin","Ministatement","EXIT"};
					   for(int i=0;i<func.length;i++) {
						   System.out.println(i+1+" "+func[i]);
					   }
					   System.out.println("PRESS 1/2/3/4/5/6");
					   int opt=sc.nextInt();
					   switch(opt) {
					   case 1:
						   Balanceinquiry temp=new Balanceinquiry(present_acc,data);
						   temp.execute();
						   break;
					   case 2:
						   Withdrawal temp1=new Withdrawal(present_acc,data,cash);
						   temp1.execute();
						   break;
					   case 3:
						   Deposit temp2=new Deposit(present_acc,data);
						   temp2.execute();
						   break; 
					   case 4:
						   data.changepin(present_acc,present_pin);
					   case 5:
						   for(int i=0;i<data.data.get(present_acc).pl.size();i++) {
							   System.out.println("This amount "+data.data.get(present_acc).pl.get(i).amount+"  has been "+data.data.get(present_acc).pl.get(i).opern);
						   }
						   
						   
					   }
					   isuserauthenticated=false;
					   if(opt==6) {
							  System.out.println("Leaving ATM...Thanks for visiting!!");
							   break;
					   }
					   
					  }
			 }
		 if(option==0) {
			 break;
		 }
	   }
	}
	  
}
public class ATM_CS{
	public static void main(String[] args) {
		ATM obj=new ATM();
		obj.displayMenu();
	}
}

