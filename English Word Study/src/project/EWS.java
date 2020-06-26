package project;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.VocaDAO;
import dao.VocaDAO.VocaDTO;

import project.SW;
import project.MV;

public class EWS extends JFrame{
	private int WIDTH = 1000;
	private int HEIGHT = 800;
	
	private Container contentPane;
	private ImageIcon logo = new ImageIcon("image/EWSlogo.png");	//EWS Logo 사진
	private JLabel EWSlogo = new JLabel(logo);						//EWS 사진을 보일 Label
	private JButton StudyB = new JButton(new ImageIcon("image/StudyB.png"));	//'공부하기'버튼
	private JButton MoreB = new JButton(new ImageIcon("image/moreviewB.png"));	//'더보기' 버튼
	private JPanel LogoP = new JPanel();	//Logo를 배치할 Panel
	private JPanel ButtonP = new JPanel();	//Button을 배치할 Panel
	private JPanel EmptyP = new JPanel();	//빈 공간을 만들어줄 Panel
	
	public EWS()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/4, dim.height/8);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setSize(WIDTH, HEIGHT);
		contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new FlowLayout());
		
		StudyB.setPreferredSize(new Dimension(170, 175));
		StudyB.setBorderPainted(false);
		StudyB.setContentAreaFilled(false);
		StudyB.setOpaque(false);
		StudyB.setActionCommand("StudyB");
		StudyB.addActionListener(buttonClick);
		
		MoreB.setPreferredSize(new Dimension(170, 175));
		MoreB.setBorderPainted(false);
		MoreB.setContentAreaFilled(false);
		MoreB.setOpaque(false);		
		MoreB.setActionCommand("MoreB");
		MoreB.addActionListener(buttonClick);
		
		LogoP.setLayout(new BorderLayout());
		LogoP.setBackground(Color.WHITE);
		LogoP.setPreferredSize(new Dimension(WIDTH, 560));
		LogoP.add(EWSlogo, BorderLayout.SOUTH);
		
		EmptyP.setBackground(Color.WHITE);
		EmptyP.setPreferredSize(new Dimension(620, 170));

		ButtonP.setBackground(Color.WHITE);
		ButtonP.setPreferredSize(new Dimension(WIDTH, 200));
		ButtonP.add(MoreB);
		ButtonP.add(EmptyP);
		ButtonP.add(StudyB);
		
		contentPane.add(LogoP);
		contentPane.add(MoreB);
		contentPane.add(EmptyP);
		contentPane.add(StudyB);

		this.setTitle("[8조] English Word Study");
		this.setBackground(Color.WHITE);
		this.setResizable(false);
		this.setVisible(true);
		/*
		VocaDAO vocaDao =new VocaDAO();
		//값읽어올떄
		ArrayList<VocaDTO> result= vocaDao.read("default");
		for(int i=0; i< result.size(); i++) {
			System.out.println("단어 "+
			((VocaDTO)result.get(i)).getVoca()
			+" 뜻 "+
			((VocaDTO)result.get(i)).getMean());
			
		}
		//틀린단어 가져올떄
		ArrayList<VocaDTO> result2= vocaDao.read("failed");
		for(int i=0; i< result2.size(); i++) {
			System.out.println("오답 "+
			((VocaDTO)result2.get(i)).getVoca()
			+" 뜻 "+
			((VocaDTO)result2.get(i)).getMean());
			
		}
		//단어 검색할때
				ArrayList<VocaDTO> result3= vocaDao.read("단어1");
				if(result3.size()==0) {
					//단어없음
				}
				for(int i=0; i< result3.size(); i++) {
					System.out.println("검색 : "+
					((VocaDTO)result3.get(i)).getVoca()
					+" 뜻 "+
					((VocaDTO)result3.get(i)).getMean());
					
				}
		*/
	
	}
	
	public EWS(int in)
	{		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/4, dim.height/8);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setSize(WIDTH, HEIGHT);
		contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);	
		contentPane.setLayout(new FlowLayout());
		
		StudyB.setPreferredSize(new Dimension(170, 175));
		StudyB.setBorderPainted(false);
		StudyB.setContentAreaFilled(false);
		StudyB.setOpaque(false);
		StudyB.setActionCommand("StudyB");
		StudyB.addActionListener(buttonClick);
		
		MoreB.setPreferredSize(new Dimension(170, 175));
		MoreB.setBorderPainted(false);
		MoreB.setContentAreaFilled(false);
		MoreB.setOpaque(false);		
		MoreB.setActionCommand("MoreB");
		MoreB.addActionListener(buttonClick);
		
		LogoP.setLayout(new BorderLayout());
		LogoP.setBackground(Color.WHITE);
		LogoP.setPreferredSize(new Dimension(WIDTH, 560));
		LogoP.add(EWSlogo, BorderLayout.SOUTH);
		
		EmptyP.setBackground(Color.WHITE);
		EmptyP.setPreferredSize(new Dimension(620, 170));

		ButtonP.setBackground(Color.WHITE);
		ButtonP.setPreferredSize(new Dimension(WIDTH, 200));
		ButtonP.add(MoreB);
		ButtonP.add(EmptyP);
		ButtonP.add(StudyB);
		
		contentPane.add(LogoP);
		contentPane.add(MoreB);
		contentPane.add(EmptyP);
		contentPane.add(StudyB);

		this.setTitle("[8조] English Word Study");
		this.setBackground(Color.WHITE);
		this.setResizable(false);
		this.setVisible(false);
	}
		
	private ActionListener buttonClick =new ActionListener() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{			
			if(e.getActionCommand().equals("StudyB")) 
			{
				SW sw = new SW();
				System.out.println("StudyB클릭");
				//SW로 화면 전환
				setVisible(false);
				sw.setVisible(true);
			}
			else if(e.getActionCommand().equals("MoreB")) 
			{
				MV mv = new MV();
				System.out.println("MoreB클릭");
				//TW로 화면전환
				setVisible(false);
				mv.setVisible(true);
			}
		}
	};
	
	public static void main(String[] args) 
	{
		new EWS();
	}
}
