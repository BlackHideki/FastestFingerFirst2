package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import base.Execute;
import base.GameFlg;
import base.Scene;
import file.ImageFileReader;
import file.SoundFileReader;
import file.TextFileReader;

public class TitleScene implements Scene {

	/**
	 * 画像
	 */
	private ImageFileReader titleBG;
	private ImageFileReader titleLogo;
	private ImageFileReader mark;
	private ImageFileReader menuStart;
	private ImageFileReader menuRanking;
	private ImageFileReader menuRule;
	private ImageFileReader rankingLogo;
	private ImageFileReader rankingOne;
	private ImageFileReader rankingTwo;
	private ImageFileReader rankingThree;
	private ImageFileReader ruleBG;
	private ImageFileReader cursor;
	private ImageFileReader menuBack;

	/**
	 * BGM
	 */
	private SoundFileReader bgm;

	/**
	 * カーソル移動音
	 */
	private SoundFileReader cursorMoveSE;

	/**
	 * 決定音
	 */
	private SoundFileReader dicideSE;

	/**
	 * ランキング
	 */
	private TextFileReader ranking;

	/**
	 * カーソルの表示座標
	 */
	private ArrayList<Point> cursorPointList;

	/**
	 * カーソルリストのインデックス
	 */
	private int cursorPointListIndex;

	/**
	 * ゲームフラグ
	 */
	private GameFlg gameFlg;

	/**
	 * TitleScene のオブジェクトを新しく生成する
	 */
	public TitleScene() {
		titleBG = new ImageFileReader("images/title_bg.png");

		titleLogo = new ImageFileReader("images/logo.png");

		mark = new ImageFileReader("images/mark.png", 100, 100);

		menuStart = new ImageFileReader("images/menu_start.png", 120, 55);

		menuRanking = new ImageFileReader("images/menu_ranking.png", 165, 55);

		menuRule = new ImageFileReader("images/menu_rule.png", 90, 55);

		rankingLogo = new ImageFileReader("images/menu_ranking.png");

		rankingOne = new ImageFileReader("images/ranking_one.png", 100, 100);

		rankingTwo = new ImageFileReader("images/ranking_two.png", 70, 70);

		rankingThree = new ImageFileReader("images/ranking_three.png", 50, 50);

		ruleBG = new ImageFileReader("images/rule.png", 800, 540);

		cursor = new ImageFileReader("images/cursor.png", 55, 55);

		menuBack = new ImageFileReader("images/menu_back.png", 101, 55);

		bgm = new SoundFileReader("sound/title_bgm.wav");

		cursorMoveSE = new SoundFileReader("sound/cursor.wav");

		dicideSE = new SoundFileReader("sound/dicide.wav");

		ranking = new TextFileReader("text/ranking.txt");
		ranking.textRead();

		cursorPointList = new ArrayList<>();
		cursorPointList.add(new Point(Execute.WINDOW_WIDTH / 2 - menuStart.getSize().width, Execute.WINDOW_HEIGHT / 3 + menuStart.getSize().height));
		cursorPointList.add(new Point(Execute.WINDOW_WIDTH / 2 - menuRanking.getSize().width, Execute.WINDOW_HEIGHT / 3 + menuRanking.getSize().height * 2));
		cursorPointList.add(new Point(Execute.WINDOW_WIDTH / 2 - menuRule.getSize().width, Execute.WINDOW_HEIGHT / 3 + menuRule.getSize().height * 3));
	}

	/**
	 * 初期化
	 */
	@Override
	public void init() {
		bgm.loop();

		ranking.textRead();

		cursorPointListIndex = 0;

		gameFlg = GameFlg.Top;
	}

	/**
	 * 処理
	 */
	@Override
	public void action() {

	}

	/**
	 * キーが押された瞬間に呼び出される
	 */
	@SuppressWarnings("incomplete-switch")
	@Override
	public void keyPressed(int key) {
		switch (gameFlg) {
		case Top:
			switch (key) {
			case KeyEvent.VK_UP:
				cursorMoveSE.play();
				if (cursorPointListIndex == 1) {
					cursorPointListIndex = 0;
				} else if (cursorPointListIndex == 2) {
					cursorPointListIndex = 1;
				}
				break;

			case KeyEvent.VK_DOWN:
				cursorMoveSE.play();
				if (cursorPointListIndex == 0) {
					cursorPointListIndex = 1;
				} else if (cursorPointListIndex == 1) {
					cursorPointListIndex = 2;
				}
				break;

			case KeyEvent.VK_ENTER:
				dicideSE.play();
				if (cursorPointListIndex == 0) {
					gameFlg = GameFlg.Next;
					bgm.stop();
				} else if (cursorPointListIndex == 1) {
					gameFlg = GameFlg.Ranking;
				} else {
					gameFlg = GameFlg.Rule;
				}
				break;
			}
			break;

		case Ranking:
			if (key == KeyEvent.VK_ENTER) {
				dicideSE.play();
				gameFlg = GameFlg.Top;
			}
			break;

		case Rule:
			if (key == KeyEvent.VK_ENTER) {
				dicideSE.play();
				gameFlg = GameFlg.Top;
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
	 * キーがクリックされた瞬間に呼び出される
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
			graphics.drawImage(titleBG.getImage(), 0, 0, null);

			graphics.drawImage(titleLogo.getImage(), Execute.WINDOW_WIDTH / 2 - titleLogo.getSize().width / 2, Execute.WINDOW_HEIGHT / 4, null);

			graphics.drawImage(mark.getImage(), 320, Execute.WINDOW_HEIGHT / 4 - titleLogo.getSize().height - 30, null);
			graphics.drawImage(mark.getImage(), 380, Execute.WINDOW_HEIGHT / 4 - titleLogo.getSize().height - 30, null);

			graphics.drawImage(menuStart.getImage(), Execute.WINDOW_WIDTH / 2 - menuStart.getSize().width / 2, Execute.WINDOW_HEIGHT / 3 + menuStart.getSize().height, null);
			graphics.drawImage(menuRanking.getImage(), Execute.WINDOW_WIDTH / 2 - menuRanking.getSize().width / 2, Execute.WINDOW_HEIGHT / 3 + menuRanking.getSize().height * 2, null);
			graphics.drawImage(menuRule.getImage(), Execute.WINDOW_WIDTH / 2 - menuRule.getSize().width / 2, Execute.WINDOW_HEIGHT / 3 + menuRule.getSize().height * 3, null);

			graphics.drawImage(cursor.getImage(), cursorPointList.get(cursorPointListIndex).x, cursorPointList.get(cursorPointListIndex).y, null);
			break;

		case Ranking:
			graphics.drawImage(titleBG.getImage(), 0, 0, null);

			graphics.setColor(Color.BLACK);
			graphics.drawImage(rankingLogo.getImage(), Execute.WINDOW_WIDTH / 2 - rankingLogo.getSize().width / 2, Execute.WINDOW_HEIGHT / 8, null);

			graphics.drawImage(rankingOne.getImage(), Execute.WINDOW_WIDTH / 2 - rankingLogo.getSize().width, Execute.WINDOW_HEIGHT / 8 + rankingLogo.getSize().height, null);
			graphics.setFont(new Font("メイリオ", Font.BOLD, 80));
			graphics.drawString(ranking.getText().get(0), Execute.WINDOW_WIDTH / 2 - rankingLogo.getSize().width / 2 + 25, Execute.WINDOW_HEIGHT / 4 + rankingLogo.getSize().height + 5);

			graphics.drawImage(rankingTwo.getImage(), Execute.WINDOW_WIDTH / 2 - rankingLogo.getSize().width + 15, Execute.WINDOW_HEIGHT / 8 + rankingLogo.getSize().height + rankingOne.getSize().height + 20, null);
			graphics.setFont(new Font("メイリオ", Font.BOLD, 60));
			graphics.drawString(ranking.getText().get(1), Execute.WINDOW_WIDTH / 2 - rankingLogo.getSize().width / 2 + 45, Execute.WINDOW_HEIGHT / 4 + rankingLogo.getSize().height + 100);

			graphics.drawImage(rankingThree.getImage(), Execute.WINDOW_WIDTH / 2 - rankingLogo.getSize().width + 25, Execute.WINDOW_HEIGHT / 8 + rankingLogo.getSize().height + rankingOne.getSize().height + rankingTwo.getSize().height + 40, null);
			graphics.setFont(new Font("メイリオ", Font.BOLD, 40));
			graphics.drawString(ranking.getText().get(2), Execute.WINDOW_WIDTH / 2 - rankingLogo.getSize().width / 2 + 65, Execute.WINDOW_HEIGHT / 4 + rankingLogo.getSize().height + 175);

			graphics.drawImage(menuBack.getImage(), Execute.WINDOW_WIDTH - menuBack.getSize().width - 20, Execute.WINDOW_HEIGHT - menuBack.getSize().height * 2, null);
			graphics.drawImage(cursor.getImage(), Execute.WINDOW_WIDTH - menuBack.getSize().width - 80, Execute.WINDOW_HEIGHT - menuBack.getSize().height * 2, null);
			break;

		case Rule:
			graphics.drawImage(ruleBG.getImage(), 0, 0, null);

			graphics.drawImage(menuBack.getImage(), Execute.WINDOW_WIDTH - menuBack.getSize().width - 20, Execute.WINDOW_HEIGHT - menuBack.getSize().height * 2, null);
			graphics.drawImage(cursor.getImage(), Execute.WINDOW_WIDTH - menuBack.getSize().width - 80, Execute.WINDOW_HEIGHT - menuBack.getSize().height * 2, null);
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

}
