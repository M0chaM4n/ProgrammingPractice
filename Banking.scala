import scala.io.Source
import java.nio.file.{Paths, Files}
import java.nio.charset.StandardCharsets

val file = Source.fromFile("log.html").getLines

object Banking
{
	def main(args: Array[Strings])
	{
		val command: String

		while(command != Exit)
		{
			println("Please enter in a command (Deposit, Withdraw, Balance, Exit): ")
			command = Console.readLine

			if command.equalsIgnoreCase("Deposit") 
			{
				println("Enter Deposit amount: ")
				val amount = Console.readLine
				Files.write(Paths.get(file, amount.getBytes(StandardCharsets.UTF_8))
			}
				
			else if command.equalsIgnoreCase("Withdraw")
			{
				println("Enter Withdrawal amount: ")
				val amount = Console.readLine
			}
			else if command.equalsIgnoreCase("Balance")
			{
				val amount = file.foldLeft(Option.empty[String]) {case (_, line) => Some(line)}
				println(s"Your balance is $$amount")
			}
			else if command.equalsIgnoreCase("Exit")
			{
				
			}
			else
				println("Command invalid.")
				
		}
	}
}