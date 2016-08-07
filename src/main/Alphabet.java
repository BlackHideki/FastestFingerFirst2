package main;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * アルファベットを操作する為のクラス
 *
 * @author kudo
 *
 */
public class Alphabet {

	/**
	 * アルファベット
	 */
	public static final String A = "A";
	public static final String B = "B";
	public static final String C = "C";
	public static final String D = "D";
	public static final String E = "E";
	public static final String F = "F";
	public static final String G = "G";
	public static final String H = "H";
	public static final String I = "I";
	public static final String J = "J";
	public static final String K = "K";
	public static final String L = "L";
	public static final String M = "M";
	public static final String N = "N";
	public static final String O = "O";
	public static final String P = "P";
	public static final String Q = "Q";
	public static final String R = "R";
	public static final String S = "S";
	public static final String T = "T";
	public static final String U = "U";
	public static final String V = "V";
	public static final String W = "W";
	public static final String X = "X";
	public static final String Y = "Y";
	public static final String Z = "Z";

	/**
	 * 指定されたリストにアルファベットを格納する
	 *
	 * @param list
	 * @return list
	 */
	public static ArrayList<String> addAlphabetToList(ArrayList<String> list) {
		list.add(A);
		list.add(B);
		list.add(C);
		list.add(D);
		list.add(E);
		list.add(F);
		list.add(G);
		list.add(H);
		list.add(I);
		list.add(J);
		list.add(K);
		list.add(L);
		list.add(M);
		list.add(N);
		list.add(O);
		list.add(P);
		list.add(Q);
		list.add(R);
		list.add(S);
		list.add(T);
		list.add(U);
		list.add(V);
		list.add(W);
		list.add(X);
		list.add(Y);
		list.add(Z);

		return list;
	}

	/**
	 * 指定された文字をキーコードに変換
	 *
	 * @param key
	 * @return
	 */
	public static int converterToKeyCode(String key) {
		int result = -1;

		switch (key) {
		case "A":
			result = KeyEvent.VK_A;
			break;

		case "B":
			result = KeyEvent.VK_B;
			break;

		case "C":
			result = KeyEvent.VK_C;
			break;

		case "D":
			result = KeyEvent.VK_D;
			break;

		case "E":
			result = KeyEvent.VK_E;
			break;

		case "F":
			result = KeyEvent.VK_F;
			break;

		case "G":
			result = KeyEvent.VK_G;
			break;

		case "H":
			result = KeyEvent.VK_H;
			break;

		case "I":
			result = KeyEvent.VK_I;
			break;

		case "J":
			result = KeyEvent.VK_J;
			break;

		case "K":
			result = KeyEvent.VK_K;
			break;

		case "L":
			result = KeyEvent.VK_L;
			break;

		case "M":
			result = KeyEvent.VK_M;
			break;

		case "N":
			result = KeyEvent.VK_N;
			break;

		case "O":
			result = KeyEvent.VK_O;
			break;

		case "P":
			result = KeyEvent.VK_P;
			break;

		case "Q":
			result = KeyEvent.VK_Q;
			break;

		case "R":
			result = KeyEvent.VK_R;
			break;

		case "S":
			result = KeyEvent.VK_S;
			break;

		case "T":
			result = KeyEvent.VK_T;
			break;

		case "U":
			result = KeyEvent.VK_U;
			break;

		case "V":
			result = KeyEvent.VK_V;
			break;

		case "W":
			result = KeyEvent.VK_W;
			break;

		case "X":
			result = KeyEvent.VK_X;
			break;

		case "Y":
			result = KeyEvent.VK_Y;
			break;

		case "Z":
			result = KeyEvent.VK_Z;
			break;
		}

		return result;
	}

}
