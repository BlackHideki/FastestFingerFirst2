package base;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import main.GameLogic;

/**
 * ゲームロジックを構築する為に最低限必要な機能を実装する為のクラス
 * @author kudouhideki
 *
 */
public class GameLogicBase extends JPanel implements Runnable {

	/**
	 * ゲームロジックを構築する為のクラス
	 */
	private GameLogic gl;

	/**
	 * 指定された幅、高さのパネルを新しく生成する
	 */
	public GameLogicBase(int width, int height){
		setSize(width, height);

		gl = new GameLogic();

		new Thread(this).start();
	}

	/**
	 * ゲームループ
	 */
	@Override
	public void run() {
		while(true){
			action();
			repaint();
			try{
				Thread.sleep(1000/60);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * 処理
	 */
	public void action(){
		gl.action();
	}

	/**
	 * 描画
	 */
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		gl.paint(graphics);
	}

	/**
	 * キーが押された瞬間に呼び出される
	 * @param key 押されたキーコード
	 */
	public void keyPressed(int key) {
		gl.keyPressed(key);
	}

	/**
	 * キーが離された瞬間に呼び出される
	 */
	public void keyReleased() {
		gl.keyReleased();
	}

	/**
	 * マウスがクリックした瞬間に呼び出される
	 * @param position クリックした瞬間の座標
	 */
	public void mouseClick(Point position) {
		gl.mouseClick(position);
	}
}
