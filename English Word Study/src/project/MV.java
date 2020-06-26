package project;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.Common;

import dao.VocaDAO;
import dao.VocaDAO.VocaDTO;

import project.EWS;
import project.RW;

class AddVocaDlog extends JDialog
{
	private int WIDTH = 400;
	private int HEIGHT = 300;
	
	private Container contentPane;
	private JPanel TopP = new JPanel();
	private JPanel EmptyP = new JPanel();
	private JPanel VocaP = new JPanel();
	private JPanel MeanP = new JPanel();
	private JPanel BottomP = new JPanel();	
	private JLabel VocaL = new JLabel("영단어");
	private JLabel MeanL = new JLabel("영단어 뜻");
	private JButton CheckB = new JButton("확인");
	private JButton CancelB = new JButton("취소");
	private JTextField VocaT = new JTextField();
	private JTextField MeanT = new JTextField();
	
	public AddVocaDlog()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/3, dim.height/4);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setSize(WIDTH, HEIGHT);
		contentPane = getContentPane();	
		contentPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		VocaT.setPreferredSize(new Dimension(200, 30));
		MeanT.setPreferredSize(new Dimension(200, 30));
		
		CheckB.setBackground(new Color(85,142,213));
		CheckB.setActionCommand("CheckB");
		CheckB.addActionListener(buttonClick);
		
		CancelB.setBackground(new Color(85,142,213));
		CancelB.setActionCommand("CancelB");
		CancelB.addActionListener(buttonClick);
		
		VocaP.setPreferredSize(new Dimension(120, 35));
		VocaP.setLayout(new FlowLayout(FlowLayout.RIGHT));
		VocaP.add(VocaL);
		
		MeanP.setPreferredSize(new Dimension(120,35));
		MeanP.setLayout(new FlowLayout(FlowLayout.RIGHT));
		MeanP.add(MeanL);
		
		EmptyP.setPreferredSize(new Dimension(WIDTH, 50));
		
		TopP.setLayout(new FlowLayout());
		TopP.setPreferredSize(new Dimension(WIDTH, 200));
		TopP.add(EmptyP);
		TopP.add(VocaP);
		TopP.add(VocaT);
		TopP.add(MeanP);
		TopP.add(MeanT);

		BottomP.setLayout(new FlowLayout(FlowLayout.RIGHT));
		BottomP.setPreferredSize(new Dimension(WIDTH, 90));
		BottomP.add(CheckB);
		BottomP.add(CancelB);
		
		contentPane.add(TopP);
		contentPane.add(BottomP);

		setTitle("영단어 추가");
		setBackground(Color.WHITE);
		setResizable(false);
	}
	
	private ActionListener buttonClick =new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getActionCommand().equals("CheckB")) 
			{

				VocaDAO vocaDao =new VocaDAO();
				//값넣을떄
				String v1 =VocaT.getText().toString(); 
				String v2= MeanT.getText().toString();
				
				if(v1.length()==0 || v2.length() ==0) {
					Common.showDialog("1글자 이상");
					return;
				}
				
				VocaDTO voca =new VocaDTO(-1,v1,v2);
				vocaDao.create(voca); 
				

				Common.showDialog("단어등록완료");
				VocaT.setText("");
				MeanT.setText("");
				setVisible(false);			
			}
			else if(e.getActionCommand().equals("CancelB")) 
			{
				setVisible(false);			
			}
		}
	};
	
	public static void main(String[] args) {
		new AddVocaDlog();
	}
	
	
}


class SearchVocaDlog extends JDialog
{
	private int WIDTH = 400;
	private int HEIGHT = 300;
	
	private Container contentPane;
	private JPanel TopP = new JPanel();
	private JPanel EmptyP = new JPanel();
	public JPanel EmptyBottomP = new JPanel();
	private JLabel EmptyL = new JLabel("검색결과가 없습니다.");
	public JPanel BottomP = new JPanel();
	private JLabel Label = new JLabel("");
	private JButton SearchB = new JButton("검색");
	private JButton AddB = new JButton("단어추가");
	private JTextField SearchT = new JTextField();
	
	public SearchVocaDlog()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/3, dim.height/4);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setSize(WIDTH, HEIGHT);
		contentPane = getContentPane();	
		contentPane.setLayout(new FlowLayout());
		
		EmptyP.setPreferredSize(new Dimension(WIDTH, 10));
		
		SearchT.setPreferredSize(new Dimension(250, 35));

		AddB.setBackground(new Color(85,142,213));
		AddB.setActionCommand("AddB");
		AddB.addActionListener(buttonClick);		

		SearchB.setBackground(new Color(85,142,213));
		SearchB.setActionCommand("SearchB");
		SearchB.addActionListener(buttonClick);
		
		TopP.setPreferredSize(new Dimension(WIDTH, 100));
		TopP.add(EmptyP);
		TopP.add(SearchT);
		TopP.add(SearchB);
		
		EmptyBottomP.setPreferredSize(new Dimension(WIDTH, 180));
		EmptyBottomP.add(EmptyL);
		EmptyBottomP.add(EmptyP);
		EmptyBottomP.add(AddB);
		EmptyBottomP.setVisible(false);		

		BottomP.setPreferredSize(new Dimension(WIDTH-10, 180));
		//BottomP.add(EmptyP);
		BottomP.add(Label);
		BottomP.setVisible(false);
		
		contentPane.add(TopP);
		contentPane.add(BottomP);	
		contentPane.add(EmptyBottomP);	

		setTitle("영단어 검색");
		setBackground(Color.WHITE);
		//setResizable(false);
	}
	
	private ActionListener buttonClick =new ActionListener() 
	{
		AddVocaDlog add = new AddVocaDlog();
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			VocaDAO vocaDao =new VocaDAO();
			//DB에서 해당 영어나 뜻을 가진 단어 찾기
			if(e.getActionCommand().equals("SearchB")) 
			{
				String voca  =SearchT.getText().toString();
				
				if(voca.length()==0) {
					Common.showDialog("1자 이상");
					return;
				}
				
				ArrayList<VocaDTO> result= vocaDao.read(voca);
				if(result.size()==0) {
					Common.showDialog("단어 없음");
					EmptyBottomP.setVisible(true);
					BottomP.setVisible(false);
				}
				else 
				{
					EmptyBottomP.setVisible(false);
					BottomP.setVisible(true);
					for(int i=0; i <result.size(); i++) 
					{
						VocaDTO theVoca = result.get(i);
						Label.setText(theVoca.getVoca()+" : "+theVoca.getMean());
						System.out.println(theVoca.getVoca()+" : "+theVoca.getMean());
					}
				}
			
			}
			else if(e.getActionCommand().equals("AddB")) 
			{
				setVisible(false);
				add.setVisible(true);
			}
		}
	};
	
	public static void main(String[] args) {
		new SearchVocaDlog();
	}
}

public class MV extends JFrame{
	private int WIDTH = 1000;
	private int HEIGHT = 800;
	
	private Container contentPane;
	private ImageIcon logo = new ImageIcon("image/MoreViewlogo.png");
	private JLabel Morelogo = new JLabel(logo);		
	private JButton AddB = new JButton(new ImageIcon("image/AddB.png"));
	private JButton HomeB = new JButton(new ImageIcon("image/HomeB.png"));
	private JButton SearchB = new JButton(new ImageIcon("image/SearchB.png"));
	private JButton IncorrectB = new JButton(new ImageIcon("image/IncorrectB.png"));
	private JPanel TopP = new JPanel();
	private JPanel BottomP = new JPanel();
	private JPanel AddP = new JPanel();
	private JPanel HomeP = new JPanel();
	private JPanel SearchP = new JPanel();
	private JPanel IncorrectP = new JPanel();

	public MV()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/4, dim.height/8);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setSize(WIDTH, HEIGHT);
		contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);	
		contentPane.setLayout(new FlowLayout());

		HomeB.setPreferredSize(new Dimension(170, 170));
		HomeB.setBorderPainted(false);
		HomeB.setContentAreaFilled(false);
		HomeB.setOpaque(false);
		HomeB.setActionCommand("HomeB");
		HomeB.addActionListener(buttonClick);
		
		AddB.setPreferredSize(new Dimension(170, 170));
		AddB.setBorderPainted(false);
		AddB.setContentAreaFilled(false);
		AddB.setOpaque(false);
		AddB.setActionCommand("AddB");
		AddB.addActionListener(buttonClick);
		
		SearchB.setPreferredSize(new Dimension(170, 170));
		SearchB.setBorderPainted(false);
		SearchB.setContentAreaFilled(false);
		SearchB.setOpaque(false);
		SearchB.setActionCommand("SearchB");
		SearchB.addActionListener(buttonClick);
		
		IncorrectB.setPreferredSize(new Dimension(170, 170));
		IncorrectB.setBorderPainted(false);
		IncorrectB.setContentAreaFilled(false);
		IncorrectB.setOpaque(false);
		IncorrectB.setActionCommand("IncorrectB");
		IncorrectB.addActionListener(buttonClick);
		
		TopP.setLayout(new FlowLayout());
		TopP.setBackground(Color.WHITE);
		TopP.setPreferredSize(new Dimension(WIDTH, 560));
		TopP.add(Morelogo);

		HomeP.setLayout(new FlowLayout());
		HomeP.setPreferredSize(new Dimension(WIDTH/4-10, 190));
		HomeP.setBackground(Color.WHITE);
		HomeP.add(HomeB);
		
		AddP.setLayout(new FlowLayout());
		AddP.setPreferredSize(new Dimension(WIDTH/4-10, 190));
		AddP.setBackground(Color.WHITE);
		AddP.add(AddB);
		
		SearchP.setLayout(new FlowLayout());
		SearchP.setPreferredSize(new Dimension(WIDTH/4-10, 190));
		SearchP.setBackground(Color.WHITE);
		SearchP.add(SearchB);

		IncorrectP.setLayout(new FlowLayout());
		IncorrectP.setPreferredSize(new Dimension(WIDTH/4-10, 190));
		IncorrectP.setBackground(Color.WHITE);
		IncorrectP.add(IncorrectB);		

		BottomP.setLayout(new FlowLayout());
		BottomP.setPreferredSize(new Dimension(WIDTH, 200));
		BottomP.setBackground(Color.WHITE);
		BottomP.add(HomeP);
		BottomP.add(AddP);	
		BottomP.add(SearchP);	
		BottomP.add(IncorrectP);	
		
		contentPane.add(TopP);
		contentPane.add(BottomP);	
		
		setTitle("[8조] English Word Study");
		setBackground(Color.WHITE);
		setResizable(false);
		setVisible(false);
	}

	private ActionListener buttonClick =new ActionListener() 
	{
		AddVocaDlog add = new AddVocaDlog();
		SearchVocaDlog sea = new SearchVocaDlog();
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getActionCommand().equals("HomeB"))
			{
				EWS ews = new EWS(1);
				System.out.println("HomeB클릭");
				//EWS로 화면전환
				setVisible(false);
				ews.setVisible(true);
			}
			else if(e.getActionCommand().equals("AddB")) 
			{
				System.out.println("AddB클릭");
				add.setVisible(true);				
			}
			else if(e.getActionCommand().equals("SearchB")) 
			{
				System.out.println("SearchB클릭");	
				sea.setVisible(true);
			}
			else if(e.getActionCommand().equals("IncorrectB")) 
			{
				RW rw = new RW();
				System.out.println("IncorrectB클릭");

				VocaDAO vocaDao =new VocaDAO();
				//RW로 화면 전환
				ArrayList<VocaDTO> result = vocaDao.read("failed");
				for(int i=0; i< result.size(); i++) {
					System.out.println("오답 " + ((VocaDTO)result.get(i)).getVoca()
					+ " 뜻 " + ((VocaDTO)result.get(i)).getMean());					
				}				
				setVisible(false);
				rw.setVisible(true);
			}			
		}
	};
}
