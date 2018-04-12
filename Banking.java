
import java.util.*;
import java.text.NumberFormat;
//Jsoup is a 3rd party library that can be used to parse HTML files. It's required to run the following program and 
//can be fround from this link https://jsoup.org
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Banking
{
	public static void main(String[] arg) 
	{
		//Create the scanner used to recieve user input, as well as load the file
		Scanner input = new Scanner(System.in);
		File log = new File("log.html");
		Document account = null;
		try { account = Jsoup.parse(log,"UTF-8"); }
		catch(IOException error) { System.out.println("Unable to open ["+log.getAbsolutePath()+"] for parsing!");}
		//Infite loop as the user will decide when to exit out the loop with the 'Exit' command
		while(true)
		{
			System.out.println("Please enter in a command (Deposit, Withdraw, Balance, Exit): ");
			String command = input.next();
			if (command.equalsIgnoreCase("Deposit")) //Done so 'deposit', 'Deposit', 'DEPosit', etc all are valid commands. 
			{
				System.out.println("Please enter an amount to deposit: ");
				String amount = input.next();
				while(true)
				{
					Double val = Double.parseDouble(amount);
					String[] decimalCheck = val.toString().split("\\."); // Validate that input has two or few numbers after the decimal point
					if(val <= 0)
						System.out.println("Invalid amount, please reenter a valid amount");
					else if (decimalCheck[1].length() > 2)
						System.out.println("Too many decimals, reenter");
					else
						//write to HTML file. data contains the transactions table, and when appended to 
						//will add to the table body another containing the deposit amount the user inputted
					{
						Elements data = account.children();
						data.select("#transactions").append("<tr><td>"+amount+"</td></tr>");
						try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(log));
						writer.write(data.toString());
						writer.close();
						System.out.println("Deposit recieved");
						}
						catch (IOException e) {System.out.println("Something Broke");}
						break;
					}
				}
			}
			//Works just like Deposit but adds a negative sign
			else if	 (command.equalsIgnoreCase("Withdraw"))
			{
				System.out.println("Please enter an amount to withdraw: ");
				String amount = input.next();
				while(true)
				{
					Double val = Double.parseDouble(amount);
					String[] decimalCheck = val.toString().split("\\.");
					if(val <= 0)
						System.out.println("Invalid amount, please reenter a valid amount");
					else if (decimalCheck[1].length() > 2)
						System.out.println("Too many decimals, reenter");
					else
					{
						Elements data = account.children();
						data.select("#transactions").append("<tr><td>-"+amount+"</td></tr>");
						try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(log));
						writer.write(data.toString());
						writer.close();
						System.out.println("Withdrawal recieved");
						}
						catch (IOException e) {System.out.println("Something Broke");}
						break;
					}
				}
			}
			else if (command.equalsIgnoreCase("Balance"))
			{
				//read Html file and add everything
				double sum = 0;
				//pull all <td> elements as the table is a single row table, and then loop thru to sum them all up
				for( Element data : account.select("td"))
				{
					double i = Double.parseDouble(data.text());
					sum += i;
				}
				//Format the return value so as there are only two numbers after the decimal.
				NumberFormat dollar = NumberFormat.getCurrencyInstance();
				System.out.println("The current balance is " + dollar.format(sum));
			}
			else if (command.equalsIgnoreCase("Exit"))
				return;
			else
				System.out.println("Command invalid."); //wrong command, loop back for another command to be inputted
		}
	}
}
