package core.chapter20140720;

import java.io.File;

public class MakeDirectories {

	private static void fileData(File f) {
		System.out.println("Absolute path: " + f.getAbsolutePath()
				+ "\n Can read: " + f.canRead() + "\n Can write: "
				+ f.canWrite() + "\n getName: " + f.getName()
				+ "\n getParent: " + f.getParent() + "\n getPath: "
				+ f.getPath() + "\n length: " + f.length()
				+ "\n lastModified: " + f.lastModified());
		if (f.isFile())
			System.out.println("It’s a file");
		else if (f.isDirectory())
			System.out.println("It’s a directory");
	}

	public static void main(String[] args) {
		fileData(new File("D:/only"));
	}
}
