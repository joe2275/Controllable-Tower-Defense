package Control;

import javax.swing.*;
import View.ViewManager;
import java.awt.event.*;

public class MatchingLabelThread extends Thread {
	private Control_Manager control;
	private ViewManager ui;
	private JLabel matchingLabel;
	static private boolean animationFlag = true;
	
	public MatchingLabelThread() {
		control = Control_Manager.getInstance(null);
		ui = control.getUI();
		matchingLabel = ui.getMatchPanel().getMatchiLabel();
		animationFlag = true;
	}
	
	public void run() {
		int flag = 0;
		while(animationFlag) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flag++;
			matchingLabel.setText(matchingLabel.getText() + ".");
			if(flag == 3) {
				matchingLabel.setText("Matching.");
				flag = 0;
			}
		}
	}
	
	static public void disableMatching() {
		animationFlag = false;
	}
}

class MatchCancelBtnMouseListener extends MouseAdapter {
	private Control_Manager control;
	private ViewManager ui;
	private JLabel cancelLabel;
	
	final private ImageIcon FOCUS_BUTTON = new ImageIcon("Image/Label/pressed_button.png");
	final private ImageIcon BUTTON = new ImageIcon("Image/Label/button.png");
	
	public MatchCancelBtnMouseListener() {
		control = Control_Manager.getInstance(null);
		ui = control.getUI();
		cancelLabel = ui.getMatchPanel().getCancelLabel();
	}
	
	// 버튼 이미지 전환 이벤트
	public void mouseEntered(MouseEvent e) {
		cancelLabel.setIcon(FOCUS_BUTTON);
	}
	
	public void mouseExited(MouseEvent e) {
		cancelLabel.setIcon(BUTTON);
	}
	
	// 패널 전환 이벤트
	public void mouseClicked(MouseEvent e) {
		ui.getCard().show(ui.getContentPane(), "main");
		MatchingLabelThread.disableMatching();
		/*
		 * 서버 관련 추가 코딩 필요한 부분
		 */
		
	}
}