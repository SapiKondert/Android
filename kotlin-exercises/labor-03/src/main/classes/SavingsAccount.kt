package main.classes

class SavingsAccount: Account() {
    private var intrestRate:Double = 1.2

    fun applyIntrest(){
        deposit(getBalance()*intrestRate)
    }

    override fun displayInfo(){
        println("Savings Account")
        super.displayInfo()
        println("Intrest Rate: $intrestRate")
    }
}