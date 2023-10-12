package main

import main.classes.CheckingAccount
import main.classes.Client
import main.classes.Order
import main.classes.SavingsAccount

fun main(args: Array<String>) {
    val client1 = Client("John Doe", "123 Main St")
    val client2 = Client("Alice Smith", "456 Main St")
    val cAccount1 = CheckingAccount()
    val sAccount1 = SavingsAccount()
    client1.addAccount(cAccount1)
    client2.addAccount(sAccount1)
    //client1.displayAccounts("")
    client1.getAccount("1")?.deposit(100.0)
    client2.getAccount("2")?.deposit(200.0)
    client1.getAccount("1")?.withdraw(50.0)
    client2.getAccount("2")?.withdraw(75.0)
    client2.applyIntrest()
    client1.displayAccounts()
    client2.displayAccounts()
    client1.printAllSavingsAccounts();
    client1.calculateTotalAmountOfMoney()
    client1.removeAccount("1")
    println("----------------------------")
    client1.displayAccounts()
    client2.addAccount(SavingsAccount())
    client2.addAccount(SavingsAccount())
    client2.getAccount("2")?.deposit(1000.0)
    client2.getAccount("3")?.deposit(20.0)
    println("----------------------------")
    client2.sortAccounts(Order.BALANCE)
    client2.displayAccounts()
    println("----------------------------")
    client2.sortAccounts(Order.ACCOUNT_NAME)
    client2.displayAccounts()
}