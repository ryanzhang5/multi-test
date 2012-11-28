package courses.hibernate.entity.main;

import java.util.Date;

import org.hibernate.Session;

import courses.hibernate.entity.service.AccountService;
import courses.hibernate.entity.vo.Account;
import courses.hibernate.util.HibernateUtil;

public class Main {
	public static void testCreateAccount() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Account account = new Account();
		account.setAccountType(Account.ACCOUNT_TYPE_SAVINGS);
		account.setCreationDate(new Date());
		account.setBalance(1000L);

		Account account2 = new Account();
		account2.setAccountType(Account.ACCOUNT_TYPE_SAVINGS);
		account2.setCreationDate(new Date());
		account2.setBalance(2000L);

		AccountService accountService = new AccountService();
		accountService.saveOrUpdateAccount(account);
		accountService.saveOrUpdateAccount(account2);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
		System.out.println(account);
		System.out.println(account2);
	}

	public static void testGetAccount() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		AccountService accountService = new AccountService();
		Account account = accountService.getAccount(2L);
		System.out.println(account);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void testUpdateAccountBalance() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		AccountService accountService = new AccountService();
		Account account = accountService.getAccount(2L);
		account.setBalance(5000);
		accountService.saveOrUpdateAccount(account);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void testDeleteAccount() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		AccountService accountService = new AccountService();
		Account account = accountService.getAccount(1L);
		accountService.deleteAccount(account);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}

	public static void main(String[] args) {
		// testCreateAccount();
		//testGetAccount();
		testUpdateAccountBalance();
		// testDeleteAccount();
	}

}
