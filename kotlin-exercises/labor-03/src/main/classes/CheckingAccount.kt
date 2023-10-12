package main.classes

class CheckingAccount:Account() {

    private val overdraftLimit: Double = 0.0

    override fun withdraw(amount:Double){
        if (amount > overdraftLimit){
            println("Overdraft Limit")
        }
        else{
            super.withdraw(amount)
        }
    }

    override fun displayInfo(){
        println("Checking Account")
        super.displayInfo()
        println("Overdraft Limit: $overdraftLimit")
    }
}