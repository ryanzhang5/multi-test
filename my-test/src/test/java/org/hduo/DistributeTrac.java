package org.hduo;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;

import org.testng.annotations.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXid;

public class DistributeTrac {
	
	//public static void main(String  args[]){
	//	DistributeTrac dt = new DistributeTrac();
		
	//	dt.dt();
	//}
	
	@Test
	public void dt() {
		MysqlXADataSource xda1 = new MysqlXADataSource();
		MysqlXADataSource xda2 = new MysqlXADataSource();

		xda1.setUser("root");
		xda1.setPassword("123456");
		xda1.setUrl("jdbc:mysql://localhost:3306/bookstore");

		xda2.setUser("root");
		xda2.setPassword("123456");
		xda2.setUrl("jdbc:mysql://localhost:3306/pcstore");

		XAConnection xconn1 = null;
		XAConnection xconn2 = null;

		XAResource xares1, xares2;

		Connection conn1, conn2;

		MysqlXid xid1 = new MysqlXid(new byte[] { 0x01 }, new byte[] { 0x02 },100);
		MysqlXid xid2 = new MysqlXid(new byte[] { 0x01 }, new byte[] { 0x03 },100);
		try {
			xconn1 = xda1.getXAConnection();
			xconn2 = xda2.getXAConnection();

			conn1 = xconn1.getConnection();
			conn2 = xconn2.getConnection();

			xares1 = xconn1.getXAResource();
			xares2 = xconn2.getXAResource();

			xares1.start(xid1, XAResource.TMNOFLAGS);
			Statement stmt = conn1.createStatement();
			stmt.executeUpdate("insert into students (name) values ('ryan44444')");
			System.out.println("-----------------------111111111-------------------");
			xares1.end(xid1, XAResource.TMSUCCESS);

			xares2.start(xid1, XAResource.TMNOFLAGS);
			//xares2.start(xid2, XAResource.TMNOFLAGS);
			Statement stmt2 = conn2.createStatement();
			stmt2.executeUpdate("insert into laptap2 (name) values ('mac333')");
			System.out.println("-----------------------22222222-------------------");
			xares2.end(xid2, XAResource.TMSUCCESS);

			
			int rc1 = xares1.prepare(xid1);
			int rc2 = xares2.prepare(xid2);
			if (rc1 == XAResource.XA_OK && rc2 == XAResource.XA_OK) {
				xares1.commit(xid1, false);
				xares2.commit(xid2, false);
			} else {
				xares1.rollback(xid1);
				xares2.rollback(xid2);
			}

			conn1.close();
			xconn1.close();

			conn2.close();
			xconn2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
