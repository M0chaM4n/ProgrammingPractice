import scala.io.Source
import scala.util.control.Breaks._
import java.io.FileWriter

object Banking
{
	def main(args: Array[String])
	{
		val file = Source.fromFile("log.html").getLines
		while(true)
		{
			println("Please enter in a command (Deposit, Withdraw, Balance, Exit): ")
			val command = scala.io.StdIn.readLine()

			if (command.equalsIgnoreCase("Deposit")) 
			{
				breakable {while(true){
				println("Enter Deposit amount: ")
				val amount = scala.io.StdIn.readLine()
				if (amount.toInt < 0)
					println("Invalid amount")
				else
					new FileWriter("log.html", true) { write(s"$amount\n"); close }
					break
				}}
			}
				
			else if (command.equalsIgnoreCase("Withdraw"))
			{
				println("Enter Withdrawal amount: ")
				val amount = scala.io.StdIn.readLine()
				new FileWriter("log.html", true) { write(s"-$amount\n"); close }
			}

			else if (command.equalsIgnoreCase("Balance"))
			{
				val amount = file.foldLeft(Option.empty[String]) {case (_, line) => Option(line)}
				println(s"Your balance is $amount")
			}

			else if (command.equalsIgnoreCase("Exit"))
			{
				return
			}

			else
				println("Command invalid.")
		}
	}		
}

