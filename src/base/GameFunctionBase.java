package base;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

/**
 * ゲームを構築する為に最低限必要な機能を実装する為のクラス
 * @author kudouhideki
 *
 */
public class GameFunctionBase extends JFrame {

	/**
	 * ゲームロジックを構築する為に最低限必要な機能を実装する為のクラス
	 */
	private GameLogicBase glb;

	/**
	 * キーの状態(押下：true　非押下：false)
	 */
	private boolean keyFlg;

	/**
	 * 指定されたタイトル、ウィンドウの幅、高さのウィンドウを新しく生成する
	 * @param title ウィンドウのヘッドに表示される文字列
	 * @param width ウィンドウの幅
	 * @param height ウィンドウの高さ
	 */
	public GameFunctionBase(String title, int width, int height){
		setTitle(title);
		setSize(width, height);
		setResizable(false);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    enableEvents(AWTEvent.KEY_EVENT_MASK);
	    enableEvents(AWTEvent.MOUSE_EVENT_MASK);

	    glb = new GameLogicBase(width, height);
	    glb.init();

	    add(glb);

	    keyFlg = false;
	}

	/**
	 * キーイベント
	 */
	protected void processKeyEvent(KeyEvent e) {
		if(e.getID() == 400 || e.getID() == 401){
			if(!keyFlg){
				keyFlg = true;
				glb.keyPressed(e.getKeyCode());
			}
		}else if(e.getID() == 402){
			if(keyFlg){
				keyFlg = false;
				glb.keyReleased();
			}
		}
	}

	/**
	 * マウスイベント
	 */
	protected void processMouseEvent(MouseEvent e){
		if(e.getID() == 501){
			glb.mouseClick(e.getPoint());
		}else if(e.getID() == 502){}
	}

}
