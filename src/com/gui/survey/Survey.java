package com.gui.survey;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Survey extends JFrame {
	private JPanel rootPanel;
	private JTextField name;
	private JTextField age;

	private String rName;
	private String rAge;
	private String rGender;
	private String hobbyStr;
	private String rEtc;
	private String result;
	
	public Survey() {
		configureFrame(); //프레임 설정
		addRootPanel(); // 부분패널을 담은 전체 패널 생성 및 설정
		addPanelA(); // 이름 나이 textfield까지 담는 패널
		addPanelB(); // 성별 패널
		addPanelC(); // 관심사 패널
		addPanelD(); // 기타사항 및 전송, 취소버튼 패널
		
		add(rootPanel);
		setVisible(true);
		
	}
	
	public void configureFrame() {
		setTitle("설문조사");
		setBounds(0,0,500,700);
//		getContentPane().setBackground(Color.BLUE);
		setIconImage(new ImageIcon("images/search.png").getImage());
		setResizable(false); // 크기조절 제한
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
	}
	
	public void addRootPanel() {
		rootPanel = new JPanel();
		rootPanel.setBackground(Color.LIGHT_GRAY);
		rootPanel.setBounds(0, 0, 500, 700);
		rootPanel.setLayout(null);
	}
	
	public void addPanelA() {
		JPanel panelA = new JPanel();
		panelA.setBounds(10, 10, 480, 130);
		panelA.setBackground(rootPanel.getBackground());
		panelA.setLayout(null);

		JPanel p2 = new JPanel();
		p2.setBounds(10, 10, 480, 30);
		p2.setBackground(Color.white);
		
		JLabel title = new JLabel("20~30대를 대상으로 진행하는 설문입니다.");
		title.setFont(new Font("바탕",Font.BOLD,20));
		title.setBounds(10, 10, 480, 130);
		
		JPanel p3 = new JPanel();
		p3.setBounds(10, 50, 480, 70);
		p3.setBackground(panelA.getBackground());
		p3.setLayout(null);
		
		JLabel nameLabel = new JLabel("이름 :");
		nameLabel.setBounds(80,20,100,30);
		
		name = new JTextField();
		name.setBounds(130,20,80,30);
		
		JLabel ageLabel = new JLabel("나이 :");
		ageLabel.setBounds(250,20,100,30);
		
		age = new JTextField();
		age.setBounds(300,20,80,30);
		
		p2.add(title);
		panelA.add(p2);
		p3.add(nameLabel);
		p3.add(name);
		p3.add(ageLabel);
		p3.add(age);
		panelA.add(p3);
		rootPanel.add(panelA);
	}
	
	public void addPanelB() {
		JPanel panelB = new JPanel();
		panelB.setBounds(10, 150, 480, 130);
		panelB.setBackground(Color.white);
		panelB.setLayout(null);
		
		JLabel question1 = new JLabel("1번 당신의 성별은?");
		question1.setBounds(10,10,200,30);
		question1.setBackground(panelB.getBackground());
		
		JPanel radioPanel = new JPanel();
		radioPanel.setBounds(10,35,480,80);
		radioPanel.setBackground(panelB.getBackground());
		radioPanel.setLayout(new GridLayout(2, 1));
		
		JRadioButton male = new JRadioButton("남성");
		male.setBackground(panelB.getBackground());
		JRadioButton female = new JRadioButton("여성");
		female.setBackground(panelB.getBackground());
		
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(male);
		genderGroup.add(female);
		
		//리스너 등록
		class GenderSelectListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				rGender = ((JRadioButton)e.getSource()).getText()+" ";
			}
			
		}
		ActionListener listener = new GenderSelectListener();
		male.addActionListener(listener);
		female.addActionListener(listener);
		
		radioPanel.add(male);
		radioPanel.add(female);
		panelB.add(question1);
		panelB.add(radioPanel);
		rootPanel.add(panelB);
	}
	
	public void addPanelC() {
		JPanel panelC = new JPanel();
		panelC.setBackground(Color.white);
		panelC.setBounds(10, 300, 480, 130);
//		panelC.setLayout(null);
		
		JLabel question2 = new JLabel("2번 당신의 관심사는? (다중선택 가능)");
		question2.setBounds(10,10,300,30);
		question2.setBackground(panelC.getBackground());
		
		JPanel hobby = new JPanel();
		hobby.setBackground(panelC.getBackground());
		hobby.setBounds(10,50,480,50);
//		hobby.setLayout(null);
		
		JTextField etc = new JTextField();
//		etc.setBounds(300,20,80,20);
		
		String[] hobbyArr = {"음식","음악","TV","운동","기타"};
		JCheckBox[] chkArr = new JCheckBox[hobbyArr.length];
		for(int i=0;i<hobbyArr.length;i++) {
			int x = 50;
			int y = 20;
			int width = 80;
			int height = 20;
			JCheckBox chk = new JCheckBox(hobbyArr[i]);
			chk.setBackground(panelC.getBackground());
			chk.setBounds(x+10, y, width, height);
			chkArr[i] = chk;
			hobby.add(chk);
			
			//리스너 추가
			chk.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					hobbyStr = "";
					
					for(JCheckBox chk : chkArr) {
						if(chk.isSelected()) {
							hobbyStr += chk.getText()+" ";
						}
					}
					if(hobbyStr.contains("기타")) {
						hobbyStr += "("+ etc.getText()+")";
					}
					
					if("".equals(hobbyStr)) {
						hobbyStr = "";
					}
				}
			});
		}
		

		
		hobby.add(etc);
		panelC.add(hobby);
		panelC.add(question2);
		rootPanel.add(panelC);
	}
	
	public void addPanelD() {
		JPanel panelD = new JPanel();
		panelD.setBackground(rootPanel.getBackground());
		panelD.setBounds(10, 450, 480, 230);
		panelD.setLayout(null);
		
		JLabel etc = new JLabel("기타사항");
		etc.setFont(new Font("바탕",Font.BOLD,13));
		etc.setBounds(10, 10, 300, 20);
		
		JTextArea etcTextArea = new JTextArea("기타사항을 작성하세요.");
		etcTextArea.setBounds(10, 50, 460, 100);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(panelD.getBackground());
		buttonPanel.setBounds(10, 160, 460, 50);
		
		JButton save = new JButton("저장");
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rEtc = etcTextArea.getText();
				rName = name.getText()+" ";
				rAge = age.getText()+" ";
				System.out.println(rName+rAge+rGender+hobbyStr+rEtc);
			}
		});
		JButton cancel = new JButton("취소");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		buttonPanel.add(save);
		buttonPanel.add(cancel);
		panelD.add(buttonPanel);
		panelD.add(etcTextArea);
		panelD.add(etc);
		rootPanel.add(panelD);
	}
}
