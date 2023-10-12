package main.classes

class Client {
    public var name:String = ""
    public var address:String = ""
    private var accounts:MutableList<Account> = mutableListOf()

    constructor(name:String, address:String){
        this.name = name
        this.address = address
    }

    fun addAccount(account:Account){
        this.accounts.add(account)
    }

    fun displayAccounts(){
        for (account in this.accounts){
            account.displayInfo()
        }
    }

    fun printAllSavingsAccounts(){
        for (account in this.accounts){
            if (account is SavingsAccount){
                account.displayInfo()
            }
        }
    }

    fun removeAccount(accountNumber:String):Boolean{
        if (this.accounts.any { it.accountNumber == accountNumber }){
            this.accounts.removeIf { it.accountNumber == accountNumber }
            return true
        }
        return false
    }

    fun calculateTotalAmountOfMoney():Double{
        var sum = 0.0
        for (account in this.accounts){
            sum += account.getBalance()
        }
        return sum
    }

    fun sortAccounts(criteria:Order):List<Account>{
        return when (criteria){
            Order.BALANCE -> this.accounts.sortedBy { it.getBalance() }
            Order.ACCOUNT_NAME -> this.accounts.sortedBy { it.accountNumber }
        }
    }

    fun getAccount(accountNumber:String): Account? {
        return this.accounts.find { it.accountNumber == accountNumber }
    }

    fun applyIntrest(){
        for (account in this.accounts){
            if (account is SavingsAccount){
                account.applyIntrest()
            }
        }
    }
}