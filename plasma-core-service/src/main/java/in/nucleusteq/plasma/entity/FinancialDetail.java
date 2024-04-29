package in.nucleusteq.plasma.entity;

import in.nucleusteq.plasma.enums.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a Financial Details.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "financial_details")
public class FinancialDetail {
/**
 * id.
 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * ctc.
     */
    @Column(name = "ctc")
    private int ctc;
    /**
     * bonus.
     */
    @Column(name = "bonus")
    private int bonus;
    /**
     * visaExpense.
     */
    @Column(name = "visa_expense")
    private int visaExpense;
    /**
     * otherExpense.
     */
    @Column(name = "other_expense")
    private int otherExpense;
    /**
     * ctcCurrency.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "ctc_currency")
    private Currency ctcCurrency;
    /**
     * bonusCurrency.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "bonus_currency")
    private Currency bonusCurrency;
    /**
     * visaExpenseCurrency.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "visa_expense_currency")
    private Currency visaExpenseCurrency;
    /**
     * otherExpenseCurrency.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "other_expense_currency")
    private Currency otherExpenseCurrency;
    /**
     * user.
     */
    @OneToOne(mappedBy = "financialDetail")
    private Employee user;
}
