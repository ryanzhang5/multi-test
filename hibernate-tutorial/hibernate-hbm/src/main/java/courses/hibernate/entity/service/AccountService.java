package courses.hibernate.entity.service;

import courses.hibernate.entity.dao.AccountDAO;
import courses.hibernate.entity.vo.Account;

/**
 * Service layer for Account
 */
public class AccountService {
	AccountDAO accountDAO = new AccountDAO();

	/**
	 * Create a new account or update an existing one
	 * 
	 * @param account
	 *            account to be persisted
	 */
	public void saveOrUpdateAccount(Account account) {
		accountDAO.saveOrUpdateAccount(account);
	}

	/**
	 * Retrieve an account
	 * 
	 * @param accountId
	 *            identifier of the account to be retrieved
	 * @return account represented by the identifier provided
	 */
	public Account getAccount(long accountId) {
		return accountDAO.getAccount(accountId);
	}

	/**
	 * Delete account
	 * 
	 * @param account
	 *            account to be deleted
	 */
	public void deleteAccount(Account account) {
		accountDAO.deleteAccount(account);
	}
}
