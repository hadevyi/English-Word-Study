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
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.VocaDAO;
import dao.VocaDAO.VocaDTO;
import project.TR;

class CompleteDlog extends JDialog
{	
	private int WIDTH = 300;
	private int HEIGHT = 200;
	
	private Container contentPane;
	private JLabel Message = new JLabel("<html>마지막 문제까지 시험을 완료하셨습니다!<br>시험결과를 보러가겠습니다.</html>");	//알림창 문구
	private JButton CheckB = new JButton("확인");		//알림창 확인버튼
	private JPanel MessageP = new JPanel();			//알림창 문구를 배치할 Panel
	public boolean result;
	
	public CompleteDlog()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/3, dim.height/4);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setSize(WIDTH, HEIGHT);
		contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);	
		contentPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		Message.setHorizontalAlignment(SwingConstants.CENTER);		

		CheckB.setActionCommand("CheckB");
		CheckB.addActionListener(buttonClick);

		MessageP.setLayout(new BorderLayout());
		MessageP.setPreferredSize(new Dimension(300, 120));
		MessageP.setBackground(Color.WHITE);
		MessageP.add(Message, BorderLayout.CENTER);	
		
		contentPane.add(MessageP);
		contentPane.add(CheckB);
		
		result = false;

		setTitle("영단어 시험 완료");
		setBackground(Color.WHITE);
		setResizable(false);
	}
	
	public boolean getResult()
	{
		return result;
	}
	
	private ActionListener buttonClick =new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getActionCommand().equals("CheckB")) 
			{
				result = true;
				setVisible(false);
			}			
		}
	};
	
	public static void main(String[] args) {
		new CompleteDlog();
	}
}

public class TW extends JFrame{
	private int WIDTH = 1000;
	private int HEIGHT = 800;
	
	private Container contentPane;
	public JLabel CountL= new JLabel("1/30");	//좌측 상단에 30개중 몇번째임을 보이는 라벨
	private JLabel QuizL=new JLabel("지금부터 시험을 시작하겠습니다.");	//퀴즈로 나오는 '영단어의 뜻'을 보이는 라벨
	private JTextField WriteT = new JTextField("이 곳에 본인이 생각하는 영단어를 쓰세요");
	public JButton CheckB = new JButton("확인");	//한 문제의 정답을 입력하고 누르는 Button
	private JPanel TopP = new JPanel();		//번째를 나타내는 라벨을 배치랑 Panel
	private JPanel MidleP = new JPanel();
	private JPanel BottomP = new JPanel();
	public int printCnt;
	
	public ArrayList<VocaDTO> vocas;

	
	public TW(ArrayList<VocaDTO> vocas) 
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/4, dim.height/8);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.vocas=vocas;
		setSize(WIDTH, HEIGHT);
		contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);	
		contentPane.setLayout(new FlowLayout());
		
		CountL.setFont(new Font("Conslas", Font.BOLD, 70));
		CountL.setForeground(new Color(85,142,213));

		QuizL.setFont(new Font("Conslas", Font.BOLD, 60));
		QuizL.setHorizontalAlignment(SwingConstants.CENTER);
		
		WriteT.setPreferredSize(new Dimension(300, 30));
		
		CheckB.setBackground(new Color(85,142,213));
		CheckB.setForeground(Color.WHITE);
		CheckB.setActionCommand("CheckB");
		CheckB.addActionListener(buttonClick);

		TopP.setLayout(new FlowLayout(FlowLayout.RIGHT));
		TopP.setPreferredSize(new Dimension(WIDTH-30, 80));
		TopP.setBackground(Color.WHITE);
		TopP.add(CountL);

		MidleP.setLayout(new BorderLayout());
		MidleP.setPreferredSize(new Dimension(WIDTH, 440));
		MidleP.setBackground(Color.WHITE);
		MidleP.add(QuizL, BorderLayout.CENTER);

		BottomP.setBackground(Color.WHITE);
		BottomP.add(WriteT);
		BottomP.add(CheckB);
		
		contentPane.add(TopP);
		contentPane.add(MidleP);
		contentPane.add(BottomP);
		
		QuizL.setText(vocas.get(0).getMean());
		printCnt = 0;

		setTitle("[8조] English Word Study");
		setBackground(Color.WHITE);
		setResizable(false);
		setVisible(false);
		
		for(int i=0; i <this.vocas.size(); i++) 
		{
			System.out.println(vocas.get(i).getVoca());
		}
		
		fails= new ArrayList<String>();
	}
	private int hitCnt=0;//정답수
	
	//오답목록
	public ArrayList<String> fails;
	
	private ActionListener buttonClick =new ActionListener()
	{		
		public void actionPerformed(ActionEvent e){	
			System.out.println((printCnt)+" : "+vocas.get(printCnt).getMean() 
					+ " "+vocas.get(printCnt).getVoca()
					+  " " + WriteT.getText().toString());
			int passed=1;
			if(vocas.get(printCnt).getVoca().equals(WriteT.getText().toString()))
			{
				hitCnt++;
				System.out.println("정답");
			}
			else 
			{
				passed=0;
				//오답목록 저장
				fails.add(vocas.get(printCnt).getMean());
				System.out.println("오답 : "+vocas.get(printCnt).getMean());
			}

			
			
			VocaDAO vocaDAo =new VocaDAO();
			String sql ="UPDATE voca set passed = "+ passed + " WHERE idx = " + vocas.get(printCnt).getIdx();
			vocaDAo.update(sql);
			printCnt++;
			
			if(printCnt<=29)
			{				
				CountL.setText((printCnt + 1) + "/30");
				//다음 영단어 뜻을 QuizL에 출력			
				QuizL.setText(vocas.get(printCnt).getMean());
			}
			else
			{
				TR tr = new TR(vocas, fails);
				Common.showDialog("시험완료! 결과를 보러갑니다.");
				//여기서 TR화면으로 넘어가고, vocas와 fails를 같이 넘겨준다.
				tr.setVisible(true);
				setVisible(false);		
			}
			WriteT.setText("");
		}
	};
}
