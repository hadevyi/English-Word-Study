package project;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.VocaDAO.VocaDTO;

import project.RW;
import project.EWS;

public class TR extends JFrame{
	private int WIDTH = 1000;
	private int HEIGHT = 800;

	private Container contentPane;
	private JButton HomeB = new JButton(new ImageIcon("image/HomeB.png"));
	private JButton IncorrectB = new JButton(new ImageIcon("image/IncorrectB.png"));
	private JLabel CorrectCntL = new JLabel("맞은 개수 : 00개");
	private JLabel IncorrectCntL = new JLabel("틀린 개수 : 00개");
	private JPanel TopP = new JPanel();
	private JPanel EmptyP = new JPanel();
	private JPanel BottomP = new JPanel();
	private JPanel CorrectP = new JPanel();
	private JPanel IncorrectP = new JPanel();
	private JPanel ButtonP = new JPanel();
	private JPanel TableP = new JPanel();
	public int CorrectCnt;
	public int IncorrectCnt;
			
	public TR(ArrayList<VocaDTO> vocas,ArrayList<String> fails)
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/4, dim.height/8);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setSize(WIDTH, HEIGHT);
		contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);	
		contentPane.setLayout(new FlowLayout());
		
		IncorrectB.setPreferredSize(new Dimension(170, 170));
		IncorrectB.setBorderPainted(false);
		IncorrectB.setContentAreaFilled(false);
		IncorrectB.setOpaque(false);
		IncorrectB.setActionCommand("IncorrectB");
		IncorrectB.addActionListener(buttonClick);

		HomeB.setPreferredSize(new Dimension(170, 170));
		HomeB.setBorderPainted(false);
		HomeB.setContentAreaFilled(false);
		HomeB.setOpaque(false);
		HomeB.setActionCommand("HomeB");
		HomeB.addActionListener(buttonClick);
		
		String []a = {"시험번호","영단어","뜻", "시험결과"};
        String [][]b = new String[30][4];

		CorrectCnt=0;
		IncorrectCnt=0;
        
        for (int i=0; i < fails.size(); i++) {
			System.out.println("틀린단어 : " +fails.get(i)+ " " + fails.size());
		}
        
        for(int i=0; i <vocas.size(); i++) {
			System.out.println("학습단어 : " +vocas.get(i).getMean() );
        }
        
        CorrectCnt = 30-fails.size();
        IncorrectCnt =fails.size();
        
        
        for(int i=0; i< vocas.size(); i++) {
        	
        	String leanred = vocas.get(i).getMean();

        	b[i][0] = (i + 1) + "";
        	b[i][1] = vocas.get(i).getVoca();
        	b[i][2] = vocas.get(i).getMean();
        	for(int j=0; j < fails.size() ; j++) {
        		String wrong =fails.get(j);
        		if(leanred.equals(wrong)) {
        			b[i][3]="오답";
        			System.out.println("틀림 : " + wrong + "<>" +leanred + " 틀린 갯수 "+ fails.size());
        			break;
        		}else {
        			b[i][3]="정답";
        		}
        	}
        }

        DefaultTableModel model = new DefaultTableModel(b,a);
        JTable table = new JTable(model);
        DefaultTableModel m = (DefaultTableModel)table.getModel();
        JScrollPane sc = new JScrollPane(table);

		CorrectCntL.setFont(new Font("Conslas", Font.BOLD, 20));
		CorrectCntL.setHorizontalAlignment(SwingConstants.CENTER);
		CorrectCntL.setText("맞은 개수 : " + CorrectCnt + "개");
		
		IncorrectCntL.setFont(new Font("Conslas", Font.BOLD, 20));
		IncorrectCntL.setHorizontalAlignment(SwingConstants.CENTER);
		IncorrectCntL.setText("틀린 개수 : " + IncorrectCnt + "개");
        
        TableP.setLayout(new FlowLayout());
        TableP.setPreferredSize(new Dimension(WIDTH-30, 550));
        TableP.setBackground(Color.WHITE);
        TableP.add(sc);
		
        EmptyP.setPreferredSize(new Dimension(WIDTH-30, 550));
        
		TopP.setLayout(new FlowLayout());
		TopP.setPreferredSize(new Dimension(WIDTH, 560));
		TopP.setBackground(Color.WHITE);
		//TopP.add(EmptyP);
		TopP.add(TableP);

		CorrectP.setLayout(new BorderLayout());
		CorrectP.setPreferredSize(new Dimension(300, 180));
		CorrectP.setBackground(Color.WHITE);
		CorrectP.add(CorrectCntL, BorderLayout.CENTER);

		IncorrectP.setLayout(new BorderLayout());
		IncorrectP.setPreferredSize(new Dimension(300, 180));
		IncorrectP.setBackground(Color.WHITE);
		IncorrectP.add(IncorrectCntL, BorderLayout.CENTER);

		ButtonP.setPreferredSize(new Dimension(370, 180));
		ButtonP.setBackground(Color.WHITE);
		ButtonP.add(IncorrectB);
		ButtonP.add(HomeB);

		BottomP.setLayout(new FlowLayout(FlowLayout.RIGHT));
		BottomP.setPreferredSize(new Dimension(WIDTH, 190));
		BottomP.setBackground(Color.WHITE);	
		BottomP.add(CorrectP);
		BottomP.add(IncorrectP);
		BottomP.add(ButtonP);
		
		contentPane.add(TopP);
		contentPane.add(BottomP);

		setTitle("[8조] English Word Study");
		setBackground(Color.WHITE);
		setResizable(false);
		setVisible(false);
	}

	private ActionListener buttonClick =new ActionListener() 
	{		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getActionCommand().equals("HomeB")) 
			{
				EWS ews = new EWS(1);
				System.out.println("HomeB클릭");
				//EWS로 화면 전환	
				setVisible(false);
				ews.setVisible(true);			
			}
			else if(e.getActionCommand().equals("IncorrectB")) 
			{
				RW rw = new RW();
				System.out.println("IncorrectB클릭");
				//RW로 화면 전환
				setVisible(false);
				rw.setVisible(true);
			}			
		}
	};
}
