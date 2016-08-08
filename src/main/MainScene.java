package main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import base.Execute;
import base.GameFlg;
import base.Scene;
import file.ImageFileReader;
import file.SoundFileReader;
import file.TextFileReader;

public class MainScene implements Scene {

	/**
	 * フォント
	 */
	private Font font50;
	private Font font100;

	/**
	 * ランダム
	 */
	private Random random;

	/**
	 * タイトルメニュー
	 */
	private ImageFileReader menuTitle;

	/**
	 * リトライメニュー
	 */
	private ImageFileReader menuRetry;

	/**
	 * カーソル
	 */
	private ImageFileReader cursor;

	/**
	 * ＢＧＭ
	 */
	private SoundFileReader bgm;

	/**
	 * ランキングを格納
	 */
	private TextFileReader ranking;

	/**
	 * カーソルの表示座標リスト
	 */
	private ArrayList<Point> cursorPointList;

	/**
	 * カーソルの表示座標リストのインデックス
	 */
	private int cursorPointListIndex;

	/**
	 * アルファベットの全てを格納
	 */
	private ArrayList<String> alphabetList;

	/**
	 * 表示するアルファベット
	 */
	private String alphabet;

	/**
	 * タイム
	 */
	private float recodeTime;

	/**
	 * アルファベットを表示させるまでの時間
	 */
	private float displayTimer;

	/**
	 * アルファベットを表示するの時間
	 */
	private float displayTime;

	/**
	 * ゲームフラグ
	 */
	private GameFlg gameFlg;

	/**
	 * MainScene のオブジェクトを新しく生成する
	 */
	public MainScene() {
		font100 = new Font("メイリオ", Font.BOLD, 100);
		font50 = new Font("メイリオ", Font.BOLD, 50);

		random = new Random();

		menuTitle = new ImageFileReader("images/menu_title.png", 102, 55);

		menuRetry = new ImageFileReader("images/menu_retry.png", 119, 55);

		cursor = new ImageFileReader("images/cursor.png", 55, 55);

		bgm = new SoundFileReader("sound/game_bgm.wav");

		ranking = new TextFileReader("text/ranking.txt");
		ranking.textRead();

		cursorPointList = new ArrayList<>();
		cursorPointList.add(new Point(Execute.WINDOW_WIDTH / 2 - menuTitle.getSize().width, Execute.WINDOW_HEIGHT / 2 - menuTitle.getSize().height / 2));
		cursorPointList.add(new Point(Execute.WINDOW_WIDTH / 2 - menuRetry.getSize().width, Execute.WINDOW_HEIGHT / 2  + menuRetry.getSize().height));

		alphabetList = new ArrayList<>();
		alphabetList = Alphabet.addAlphabetToList(alphabetList);
	}

	/**
	 * 初期化
	 */
	@Override
	public void init() {
		bgm.loop();

		cursorPointListIndex = 0;

		alphabet = alphabetList.get(random.nextInt(alphabetList.size()));

		recodeTime = 0.00f;

		displayTimer = 0.0f;

		displayTime = (random.nextInt(9) + 1) + ((float)(random.nextInt(9) + 1) / 10);

		gameFlg = GameFlg.Top;
	}

	/**
	 * 処理
	 */
	@SuppressWarnings("incomplete-switch")
	@Override
	public void action() {
		switch (gameFlg) {
		case Ready:
			if (displayTimer < displayTime) {
				displayTimer += 1 / 60.0f;
			} else {
				gameFlg = GameFlg.Start;
				bgm.stop();
			}
			break;

		case Start:
			recodeTime += 1 / 60.00f;
			break;

		case End:
			break;
		}
	}

	/**
	 * キーが押された瞬間に呼び出される
	 */
	@SuppressWarnings("incomplete-switch")
	@Override
	public void keyPressed(int key) {
		switch (gameFlg) {
		case Top:
			if (key == KeyEvent.VK_ENTER) {
				gameFlg = GameFlg.Ready;
			}
			break;

		case Start:
			if(key == Alphabet.converterToKeyCode(alphabet)) {
				gameFlg = GameFlg.End;
				compareRanking();
			}

		case End:
			if (key == KeyEvent.VK_UP) {
				cursorPointListIndex = 0;
			} else if (key == KeyEvent.VK_DOWN) {
				cursorPointListIndex = 1;
			}

			if (key == KeyEvent.VK_ENTER) {
				if (cursorPointListIndex == 0) {
					gameFlg = GameFlg.Top;
					init();
				} else {
					gameFlg = GameFlg.Next;
				}
			}
			break;
		}
	}

	/**
	 * キーが離された瞬間に呼び出される
	 */
	@Override
	public void keyReleased() {
	}

	/**
	 * マウスをクリックした瞬間に呼び出される
	 */
	@Override
	public void mouseClick(Point position) {
	}

	/**
	 * 描画
	 */
	@SuppressWarnings("incomplete-switch")
	@Override
	public void paint(Graphics graphics) {
		switch (gameFlg) {
		case Top:
			graphics.setFont(font100);
			graphics.drawString("Start For Enter", 0, Execute.WINDOW_HEIGHT / 2);
			break;

		case Start:
			graphics.setFont(font50);
			graphics.drawString("TIME : " + String.format("%.2f", recodeTime), font50.getSize(), font50.getSize());
			graphics.setFont(font100);
			graphics.drawString(alphabet, Execute.WINDOW_WIDTH / 2 - font100.getSize() / 2, Execute.WINDOW_HEIGHT / 2);
			break;

		case End:
			graphics.setFont(font100);
			graphics.drawString("TIME : " + String.format("%.2f", recodeTime), 90, Execute.WINDOW_HEIGHT / 2 - font100.getSize());
			graphics.setFont(font50);
			graphics.drawImage(menuRetry.getImage(), Execute.WINDOW_WIDTH / 2 - menuRetry.getSize().width / 2, Execute.WINDOW_HEIGHT / 2 - menuRetry.getSize().height / 2, null);
			graphics.drawImage(menuTitle.getImage(), Execute.WINDOW_WIDTH / 2 - menuRetry.getSize().width / 2, Execute.WINDOW_HEIGHT / 2  + menuTitle.getSize().height, null);
			graphics.drawImage(cursor.getImage(), cursorPointList.get(cursorPointListIndex).x, cursorPointList.get(cursorPointListIndex).y, null);
			break;
		}
	}

	/**
	 * ゲームフラグを取得
	 * @return
	 */
	public GameFlg getGameFlg() {
		return gameFlg;
	}

	/**
	 * レコードタイムとランキングを比較
	 */
	private void compareRanking() {
		float[] tmp = new float[ranking.getText().size() + 1];
		for(int i = 0; i < tmp.length; i++){
			if(i < 3){
				tmp[i] = Float.valueOf(ranking.getText().get(i));
			}else{
				tmp[i] = 9.99f;
			}
		}

		for(float i : tmp){
			if(recodeTime < i){
				tmp[tmp.length - 1] = recodeTime;
			}
		}

		Arrays.sort(tmp);

		ranking.getText().clear();
		for(int i = 0; i < 3; i++){
			ranking.getText().add(String.format("%.2f", tmp[i]));
		}
		ranking.textWrite(ranking.getText());
	}

}
