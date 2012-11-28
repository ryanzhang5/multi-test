package courses.hibernate.entity.dao;

import org.hibernate.Session;

import courses.hibernate.entity.vo.Account;
import courses.hibernate.util.HibernateUtil;

/**
 * Data Access Object for Account
 */
public class AccountDAO {

	/**
	 * Create a new account or update an existing one
	 * 
	 * @param account
	 *            account to be persisted
	 */
	public void saveOrUpdateAccount(Account account) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(account);
	}

	/**
	 * Retrieve an account from the data store
	 * 
	 * @param accountId
	 *            identifier of the account to be retrieved
	 * @return account represented by the identifier provided
	 */
	public Account getAccount(long accountId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Account account = (Account) session.get(Account.class, accountId);
		return account;
	}

	/**
	 * Delete account from data store
	 * 
	 * @param account
	 *            account to be deleted
	 */
	public void deleteAccount(Account account) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.delete(account);
	}
}
