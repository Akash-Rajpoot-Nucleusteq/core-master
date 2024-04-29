package in.nucleusteq.plasma.dto.in.user;

import in.nucleusteq.plasma.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) for representing a Updated Details of OnBorad In DTO.
 */
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedDetailOfOnBoardInDTO {
    /**
     * Details Id.
     */
    private int id;
    /**
     * Employees's CTC.
     */
    private int ctc;
    /**
     * Employee's Bonus.
     */
    private int bonus;
    /**
     * Employee's Visa Expense.
     */
    private int visaExpense;
    /**
     * Employee's other Expense.
     */
    private int otherExpense;
    /**
     * Employee's CTC Currency.
     */
    private Currency ctcCurrency;
    /**
     * Employee's Bonus Currency.
     */
    private Currency bonusCurrency;
    /**
     * Employee's Visa Expense Currency.
     */
    private Currency visaExpenseCurrency;
    /**
     * Employee's Other Expense Currency.
     */
    private Currency otherExpenseCurrency;
    /**
     * Employee's Recruiter Manger Id.
     */
    private String recruiterManager;
    /**
     * Get Id.
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * Set Id.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Get CTC.
     * @return ctc
     */
    public int getCtc() {
        return ctc;
    }
    /**
     * Set CTC.
     * @param ctc
     */
    public void setCtc(int ctc) {
        this.ctc = ctc;
    }
    /**
     * Get Bonus.
     * @return bonus
     */
    public int getBonus() {
        return bonus;
    }
    /**
     * Set Bonus.
     * @param bonus
     */
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
    /**
     * Get Visa Expense.
     * @return visaExpense
     */
    public int getVisaExpense() {
        return visaExpense;
    }
    /**
     * Set Visa Expense.
     * @param visaExpense
     */
    public void setVisaExpense(int visaExpense) {
        this.visaExpense = visaExpense;
    }
    /**
     * Get Other Expense.
     * @return otherExpense
     */
    public int getOtherExpense() {
        return otherExpense;
    }
    /**
     * Set Other Expense.
     * @param otherExpense
     */
    public void setOtherExpense(int otherExpense) {
        this.otherExpense = otherExpense;
    }
    /**
     * Get CTC Currency.
     * @return ctcCurrency
     */
    public Currency getCtcCurrency() {
        return ctcCurrency;
    }
    /**
     * Set CTC Currency.
     * @param ctcCurrency
     */
    public void setCtcCurrency(Currency ctcCurrency) {
        this.ctcCurrency = ctcCurrency;
    }
    /**
     * Get Bonus Currency.
     * @return bonusCurrency
     */
    public Currency getBonusCurrency() {
        return bonusCurrency;
    }
    /**
     * Set Bonus Currency.
     * @param bonusCurrency
     */
    public void setBonusCurrency(Currency bonusCurrency) {
        this.bonusCurrency = bonusCurrency;
    }
    /**
     * Get Visa Expense Currency.
     * @return visaExpenseCurrency
     */
    public Currency getVisaExpenseCurrency() {
        return visaExpenseCurrency;
    }
    /**
     * Set Visa Expense Currency.
     * @param visaExpenseCurrency
     */
    public void setVisaExpenseCurrency(Currency visaExpenseCurrency) {
        this.visaExpenseCurrency = visaExpenseCurrency;
    }
    /**
     * Get Other Expense Currency.
     * @return otherExpenseCurrency
     */
    public Currency getOtherExpenseCurrency() {
        return otherExpenseCurrency;
    }
    /**
     * Set Other Expense Currency.
     * @param otherExpenseCurrency
     */
    public void setOtherExpenseCurrency(Currency otherExpenseCurrency) {
        this.otherExpenseCurrency = otherExpenseCurrency;
    }
    /**
     * Get Recruiter Manager Id.
     * @return recruiterManager
     */
    public String getRecruiterManager() {
        return recruiterManager;
    }
    /**
     * Set Recruiter Manager Id.
     * @param recruiterManager
     */
    public void setRecruiterManager(String recruiterManager) {
        this.recruiterManager = recruiterManager;
    }
}
