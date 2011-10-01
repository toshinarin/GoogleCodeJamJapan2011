import java.io.*;

public class ProblemCSolver {
	static final boolean DEBUG = true;
	static int mNumCase = 0;
	static BufferedReader mBr = null;
	static PrintWriter mPw = null;

	public static void print(String s) {
		if (DEBUG == false)
			return;
		System.out.println(s);
	}

	public static int openInput(String fileName) {
		try {
			FileReader fr = new FileReader(fileName);
			mBr = new BufferedReader(fr);
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}

	public static void closeInput() {
		try {
			mBr.close();
		} catch (Exception e) {
		}
	}

	private static boolean canWrite(File file) {
		if (file.exists() == false || file.isFile() == false
				|| file.canWrite() == false) {
			return false;
		}
		return true;
	}

	public static int openOutput(String fileName) {
		File f = new File(fileName);
		if (canWrite(f) == false) {
			print("failed to prepare out file.");
			return -1;
		}
		try {
			mPw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}

	public static void closeOutput() {
		try {
			mPw.close();
			mPw = null;
		} catch (Exception e) {
		}
	}

	public static String readLine() {
		String ret = null;
		try {
			ret = mBr.readLine();
		} catch (Exception e) {
		}
		return ret;
	}

	public static int writeLine(String str) {
		try {
			mPw.println(str);
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}

	public static int countBit(long n) {
		int num = 0;
		for (; n != 0; n &= n - 1) {
			num++;
		}
		return num;
	}
	
	public static long getNearOnlyOne(long n) {
		long m = 1;
		for (int i = 0; i < 64; i++) {
			if (m > n) {
				print("m: " + m);
				m = m / 2;
				return (m - 1);
			} else {
				m = m << 1;
			}
		}
		return 0;
	}
	
	public static String calc(long N) {
		print("N={" + N + "}");
		int sum = 0;
		long a = getNearOnlyOne(N);
		print("a: " + a + ", " + Long.toBinaryString(a));
		sum = countBit(a) + countBit(N - a);
		print("a: " + a + ", " + Long.toBinaryString(a) + ", b: " + (N - a)  + ", " + Long.toBinaryString(N - a));
		return sum + "";
	}

	public static void exec(String fileName) {
		if (openInput(fileName) != 0) {
			print("failed to open input.");
			return;
		}
		if (openOutput("out.txt") != 0) {
			print("failed to open output.");
			closeInput();
			return;
		}

		String tmpStr = null;
		String[] strArray = null;
		tmpStr = readLine();
		if (tmpStr == null) {
			return;
		}
		mNumCase = Integer.parseInt(tmpStr);

		int numCase = 0;
		String ans = null;
		String out = null;
		long N = 0;

		int i = 0;
		for (i = 0; i < mNumCase; i++) {
			tmpStr = readLine();
			N = Long.parseLong(tmpStr);
			ans = calc(N);

			out = "Case #" + (numCase + 1) + ": " + ans;
			print(out);
			if (writeLine(out) != 0) {
				break;
			}
			numCase++;
		}
		print("number of parsed case: {" + numCase + "}");
		closeInput();
		closeOutput();
	}

	public static void main(String[] args) {
		print("########### Start!! ############");
		String fileName = null;
		try {
			fileName = args[0];
			print("Open file: {" + fileName + "}");
			exec(fileName);
		} catch (Exception e) {
			print("failed to execute. error: {" + e + "}");
			e.printStackTrace();
		}
		if (fileName == null) {
			print("File name must be specified.");
			return;
		}
		print("########## Finished!! ##########");
	}
}