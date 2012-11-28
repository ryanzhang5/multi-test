package courses.hibernate.entity.vo;

import java.util.Date;

/**
 * Domain object representing an Account
 */
public class Account {
	public static final String ACCOUNT_TYPE_SAVINGS = "SAVINGS";
	public static final String ACCOUNT_TYPE_CHECKING = "CHECKING";

	private long accountId;
	private String accountType;
	private Date creationDate;
	private double balance;

	public long getAccountId() {
		return accountId;
	}

	private void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String toString() {
		return "Account [accountId=" + accountId + ", accountType="
				+ accountType + ", creationDate=" + creationDate + ", balance="
				+ balance + "]";
	}

}
