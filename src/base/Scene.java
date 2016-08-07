package base;

import java.awt.Graphics;
import java.awt.Point;

/**
 * 各シーンに最低限必要な機能を定義する為のインタフェース
 * @author kudo
 *
 */
public interface Scene {

	public void init();

	public void action();

	public void keyPressed(int key);

	public void keyReleased();

	public void mouseClick(Point position);

	public void paint(Graphics graphics);

	public GameFlg getGameFlg();
}
