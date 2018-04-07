object Banking
{
	def main(args: Array[Strings])
	{
		val command: String

		while(command != Exit)
		{
			print("Please enter in a command (Deposit, Withdraw, Balance, Exit): ")
			command = Console.readLine

			if command.equalsIgnoreCase("Deposit")
				print("Enter Deposit amount: ")
				val amount = Console.readLine
				
			else if command.equalsIgnoreCase("Withdraw")
				print("Enter Withdrawal amount: ")
				val amount = Console.readLine

			else if command.equalsIgnoreCase("Balance")

			else if command.equalsIgnoreCase("Exit")

			else
				print("Command invalid.\n")
				
		}
	}
}