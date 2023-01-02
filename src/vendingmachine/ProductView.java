package vendingmachine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProductView {
	ProductVO vo;
	ArrayList<ProductVO> productList;
	ArrayList<JLabel> lblList = new ArrayList<JLabel>();
	JTextField insertTf = new JTextField(10);//크기지정
	JLabel lblResult = new JLabel();
	JFrame frame;
	ImageIcon icon;
	JLabel lbl;
	JLabel eLbl;
	public JPanel displayProducts(JFrame frame) {
		this.frame = frame;
		JLabel priceLabel = null;
		JPanel pan;
		JPanel panC = new JPanel(new GridLayout(3,3));
		for(ProductVO vo : productList) {
			icon = new ImageIcon(vo.getImageName());
			lbl = new JLabel(icon);
			priceLabel = new JLabel(vo.getPrice()+"원",JLabel.CENTER); //가격 나오는 거
			priceLabel.setFont(new Font("중고딕", Font.BOLD,15));//가격 나오는 거 폰트 설정
			priceLabel.setBackground(Color.blue);  //가격 나오는 거 뒷배경 색 설정
			lbl.setOpaque(true);      //배경 불투명하게 
			lbl.setBackground(Color.WHITE);//배경 흰색으로
			lbl.addMouseListener(mouse); //마우스리스너
			lblList.add(lbl);         
			pan = new JPanel(new BorderLayout()); //가격라벨 추가를 위해 새 패널 만듦
			pan.add(lbl,"Center");    //음료 사진을 센터에 add
			pan.add(priceLabel,"South");//가격라벨을 사우스에 add
			panC.add(pan); //위에 패널을 다시 패널에 add
		}
		return panC;
	}
	//클릭할 때 이벤트
	MouseAdapter mouse = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			eLbl = (JLabel)e.getSource();
			for(int i = 0; i< lblList.size(); i++) {
				if(eLbl == lblList.get(i)) {
					lblList.get(i).setBackground(Color.pink);//클릭하면 핑크색으로 변함
					vo = productList.get(i);
				}
			}
			JOptionPane.showMessageDialog(frame, "제품명: " + vo.getTrade_name());//메세지창 뜸
		}
	};
	public JPanel inputPurchase() {
		//flowLayout이라 굳이 위치 지정안해줘도 됨
		JPanel panS = new JPanel();
		JLabel lblMoney = new JLabel("금액: ");
		JButton btnInsert = new JButton("투입");
		btnInsert.addActionListener(btnL);
		panS.add(lblMoney);  //금액:
		panS.add(insertTf); //금액 입력 텍필
		panS.add(btnInsert);//투입버튼
		panS.add(lblResult);//음료 사면 그 음료 사진 뜨는 라벨
		return panS;
	}
	
	ActionListener btnL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//투입버튼이 클릭되었을 때 JTextField에 입력된 금액과 현재 금액을 비교하여 양수이상이면 제품과 거스름돈이 나오게
			int money = Integer.parseInt(insertTf.getText());
			for(int i = 0; i<lblList.size(); i++) {
				if(e.getSource() == lblList.get(i)) {
					vo = productList.get(i);
				}
			}
			if(vo.getPrice()>money) {//금액이 부족하면
				JOptionPane.showMessageDialog(frame,"금액이 부족합니다.");
			}else if(vo.getPrice()<money) { //금액이 더 크면
				icon = new ImageIcon(vo.getImageName());
				lblResult.setIcon(icon);
				JOptionPane.showMessageDialog(frame, "반환금액: " + (money - vo.getPrice()));
			}else {//금액이 같으면
				icon = new ImageIcon(vo.getImageName());
				lblResult.setIcon(icon);
			}
			eLbl.setBackground(Color.WHITE);
			insertTf.setText("");
		}
	};
	
	public void setProductList(ArrayList<ProductVO> productList) {
		this.productList = productList;
	}
}
