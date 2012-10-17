package thinking.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Date;

import javax.jms.BytesMessage;

class Student implements Serializable {
	boolean sex;
	String name;
	int age;

	Student(boolean sex, String name, int age) {
		this.sex = sex;
		this.name = name;
		this.age = age;

	}

	@Override
	public String toString() {
		return "Student [sex=" + sex + ", name=" + name + ", age=" + age + "]";
	}

}

public class ByteArrayOutputTest {

	public static void main(String[] args) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
				1005);

		ObjectOutputStream os = new ObjectOutputStream(byteArrayOutputStream);
		os.writeObject(new Student(true, "my Name", 10));
		os.writeObject(new Student(true, "my Name", 20));
		os.writeObject(new Student(true, "my Name", 15));
		os.writeObject(new Date());
		os.close();

		ByteArrayOutputStream b2 = new ByteArrayOutputStream(1024);
		DataOutputStream ds = new DataOutputStream(b2);
		ds.writeInt(byteArrayOutputStream.size());

		ds.write(byteArrayOutputStream.toByteArray());

		ds.close();

		DataInputStream di = new DataInputStream(new ByteArrayInputStream(
				b2.toByteArray()));

		byte[] bytes = new byte[di.readInt()];
		di.readFully(bytes);
		ObjectInputStream os2 = new ObjectInputStream(new ByteArrayInputStream(
				bytes));
		System.out.println((Student)os2.readObject());
		System.out.println((Student)os2.readObject());
		System.out.println((Student)os2.readObject());
		System.out.println((Date)os2.readObject());
		
		

	}
}
