package main.classes

abstract class Account {
    companion object{
        var numbering = 0
    }

    private var balance :Double = 0.0
    public val accountNumber : String = generateAccountNumber()

    fun deposit(amount:Double){
        balance += amount
    }

    open fun withdraw(amount:Double){
        balance -= amount
    }

    fun getBalance():Double{
        return balance
    }

    open fun displayInfo(){
        println("Account Number: $accountNumber Balance: $balance")
    }

    fun generateAccountNumber():String{
        numbering++
        return "$numbering"
    }

}