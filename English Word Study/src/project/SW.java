package project;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.VocaDAO;
import dao.VocaDAO.VocaDTO;
import javafx.scene.web.HTMLEditorSkin.Command;
import project.TW;

public class SW extends JFrame{
	private int WIDTH = 1000;
	private int HEIGHT = 800;
	
	private Container contentPane;
	private JLabel VocaL = new JLabel("영단어 공부를 시작합니다.");				//랜덤으로 선정할 영단어를 출력할 Label
	public JButton TestB = new JButton(new ImageIcon("image/TestB.png"));	//'시험치기'버튼 (단, 30번째 출력전까지 visible is false)
	private JButton NextB = new JButton(new ImageIcon("image/NextB.png")); 	//'다음단어'버튼 
	private JPanel TopP = new JPanel();		//영단어와 영단어 뜻을 보여줄 Panel
	private JPanel StudyP = new JPanel();	//공부할 영단어와 영단어를 배치할 Panel
	private JPanel BottomP = new JPanel();	//Button을 배치할 Panel
	public int printCnt;	//label이 출력된 횟수 (단, 이가 30이상일때 visible is true)
	public int IndexCnt;
	
	public SW()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/4, dim.height/8);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setSize(WIDTH, HEIGHT);
		contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);	
		contentPane.setLayout(new FlowLayout());
		
		TestB.setPreferredSize(new Dimension(170, 170));
		TestB.setBorderPainted(false);
		TestB.setContentAreaFilled(false);
		TestB.setOpaque(false);
		TestB.setVisible(false);	//if(print_cnt >=30) TestB.setVisible(true);
		TestB.setActionCommand("TestB");
		TestB.addActionListener(buttonClick);
		
		NextB.setPreferredSize(new Dimension(170, 170));
		NextB.setBorderPainted(false);
		NextB.setContentAreaFilled(false);
		NextB.setOpaque(false);
		NextB.setActionCommand("NextB");
		NextB.addActionListener(buttonClick);
		
		VocaL.setFont(new Font("Conslas", Font.BOLD, 50));

		StudyP.setPreferredSize(new Dimension(WIDTH-100, 300));
		StudyP.setBackground(Color.WHITE);
		StudyP.add(VocaL);

		TopP.setLayout(new BorderLayout());
		TopP.setPreferredSize(new Dimension(WIDTH, 560));
		TopP.setBackground(Color.WHITE);
		TopP.add(StudyP,BorderLayout.SOUTH);	
		
		BottomP.setLayout(new FlowLayout(FlowLayout.RIGHT));
		BottomP.setPreferredSize(new Dimension(WIDTH-40, 190));
		BottomP.setBackground(Color.WHITE);
		BottomP.add(TestB);
		BottomP.add(NextB);
		
		contentPane.add(TopP);
		contentPane.add(BottomP);
		
		
		//Timer로 VocaL과 MeanL에 7초당 한개 출력 (이때도 print_cnt++;)		
		setTitle("[8조] English Word Study");
		setBackground(Color.WHITE);
		setResizable(false);
		setVisible(false);
		
		//단어목록
		VocaDAO vocaDao =new VocaDAO();
		//이러면 30개가 랜덤으로 저장이된다.
		result= vocaDao.read("default");
		
	
		for(int i=0; i< result.size(); i++) {
			System.out.println(((VocaDTO)result.get(i)).getVoca()+"  "+
			((VocaDTO)result.get(i)).getMean());
		}
		
		//7초마다 타이머이벤트발생
		mTimer.schedule(mTimerTask, 7000, 7000);
		
		//창 종료하였을떄 타이머 객체 초기화
		//이벤트리스너, 버튼클릭할떄 작업하는거랑 비슷한원리, 창닫힐떄 이벤트발생
		addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	mTimerTask.cancel();
            	mTimer.cancel();
                e.getWindow().dispose();
            }
        });
		IndexCnt = 0;
		printCnt = 1;
	}
	
	public static ArrayList<VocaDTO> result;
	public Timer mTimer=new Timer();
	public TimerTask mTimerTask=new TimerTask() {
		@Override
		public void run() {
			//7초마다 할 작업	
			
			//Array의 끝의 왔을 때 다시 처음으로 바꾸어 줌
			if(IndexCnt==30)
				IndexCnt=0;
			
			//단어를 한번씩 보여줬을 때 
			if(printCnt==30)
				TestB.setVisible(true);
			
			//단어넘기기코드작성
			VocaL.setText(result.get(IndexCnt).getVoca() +"  "+ result.get(IndexCnt).getMean());			
			System.out.println("이벤트발생 " + IndexCnt+" 프린트 발생 "+ printCnt);
			
			IndexCnt++;
			printCnt++;				
		}
	};
	
	
	private ActionListener buttonClick =new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("TestB"))
			{
				TW tw = new TW(result);
				System.out.println("TestB클릭");	
				//Timer 종료
				mTimerTask.cancel();
            	mTimer.cancel();
            	
				// TW로 화면 전환
				setVisible(false);	
				tw.setVisible(true);
			}
			else if(e.getActionCommand().equals("NextB"))
			{						
				if(IndexCnt==30)
					IndexCnt=0;
				
				if(printCnt==30)
					TestB.setVisible(true);
				
				VocaL.setText(result.get(IndexCnt%30).getVoca() +"  "+ result.get(IndexCnt%30).getMean());
				System.out.print("NextB클릭 : ");
				System.out.println("이벤트발생 " + IndexCnt +" 프린트 발생 "+ printCnt);
				printCnt++;
				IndexCnt++;
			}			
		}
	};
}
