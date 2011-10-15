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

	public static double getArea(int a, int b, double angle) {
		double ans = 0.5 * a * b * Math.sin(angle);
		
		return ans;
	}
	
	public static String calc(int K, int[] E) {
		print("K={" + K + "}, E0={" + E[0] + "}");
		java.util.Arrays.sort(E);
		double angle = 2 * Math.PI / (double)K;

		int[] EE = new int[K]; 
		int k = K / 2;
		for (int i = 0; i < k; i += 1) {
			EE[i] = E[K - (i * 2) - 1];
			EE[K - i - 1] = E[K - (i * 2) - 2];
		}
		if (K % 2 > 0) {
			print("aaaf :" + k);
			EE[k] = E[0]; 
		}

		double ans = 0;
		for (int j = 0; j < K; j++) {
			if (j == K - 1) {
				ans += getArea(EE[j], EE[0], angle);
			} else {
				ans += getArea(EE[j], EE[j + 1], angle);
			}
		}

		return Double.toString(ans);
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
		int K = 0;

		int i = 0;
		for (i = 0; i < mNumCase; i++) {
			tmpStr = readLine();
			if (tmpStr == null)
				break;
			K = Integer.parseInt(tmpStr);
			
			tmpStr = readLine();
			if (tmpStr == null)
				break;
			strArray = tmpStr.split(" ");
			if (DEBUG && strArray.length != K) {
				print("failed to parse test case.");
				return;
			}
			int[] E = new int[K];
			for (int j = 0; j < K; j++) {
				E[j] = Integer.parseInt(strArray[j]);
			}
			ans = calc(K, E);

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