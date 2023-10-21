package ru.netology.nKarskanov.domain;

public class LoanOperation extends Operation{
    private int loanId;

    public void setLoanId(int loanId){
        this.loanId = loanId;
    }

    public int getLoanId(){
        return loanId;
    }

    public LoanOperation(int id, int sum, String currency, String merchant, int loanId){
        super(id, sum, currency, merchant);
        this.loanId = loanId;
    }

    @Override
    public String toString(){
        return  "Operation{ id = " + getId()  +
                ", loandID: " + loanId +
                ", sum: "  + getSum() +
                ", currency: " + getCurrency() +
                ", merchant: " + getMerchant() + "}";
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        LoanOperation operation = (LoanOperation) o;
        return getId() == operation.getId()
                && (getSum() == operation.getSum()
                &&  loanId == operation.loanId
                && (getCurrency() != null && getCurrency().equals(operation.getCurrency()))
                && (getMerchant() != null && getMerchant().equals(operation.getMerchant())));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int res = 1;
        res = prime * res + getId();
        res = prime * res + getSum();
        res = prime * res + loanId;
        res = prime * res + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        res = prime * res + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
        return res;
    }

    @Override
    public void printToConsole() {
        System.out.println("Твой id: " + getId() + "." +
                " Id совершённого займа: " + loanId + "." +
                " Сумма займа " + getSum() +
                  getCurrency() + "." +
                " С какой целью вы производите займ: " + getMerchant() + ".");
    }
}
