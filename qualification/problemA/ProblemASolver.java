import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class ProblemASolver {
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

	public static String calc(int M, int C, int W, int[] A, int[] B) {
		print("M={" + M + "}, C={" + C + "}, W={" + W + "}");
		LinkedList<Integer> cards = new LinkedList<Integer>();
		for (int i = 0; i < M; i++) {
			cards.add(i + 1);
		}
		int a = 0;
		int b = 0;
		int t = 0;
		int card = 0;
		for (int j = 0; j < C; j++) {
			a = A[j] - 1;
			b = B[j];
			t = a + b - 1;
			for (int k = 0; k < b; k++) {
				card = cards.remove(t);
				cards.add(0, card);
			}
		}

		return cards.get(W - 1) + "";
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
		int M = 0, C = 0, W = 0;

		int i = 0;
		for (i = 0; i < mNumCase; i++) {
			tmpStr = readLine();
			if (tmpStr == null)
				break;
			strArray = tmpStr.split(" ");
			if (DEBUG && strArray.length != 3) {
				print("failed to parse test case.");
				return;
			}
			M = Integer.parseInt(strArray[0]);
			C = Integer.parseInt(strArray[1]);
			W = Integer.parseInt(strArray[2]);

			int[] A = new int[C];
			int[] B = new int[C];
			for (int j = 0; j < C; j++) {
				tmpStr = readLine();
				strArray = tmpStr.split(" ");
				A[j] = Integer.parseInt(strArray[0]);
				B[j] = Integer.parseInt(strArray[1]);
			}
			ans = calc(M, C, W, A, B);

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