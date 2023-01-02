package vendingmachine;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ProductController extends JFrame{
	ArrayList<ProductVO> pvoList;
	
	public ProductController() {
		ProductView view = new ProductView();
		fullproduct();
		view.setProductList(pvoList);
		JPanel panC = view.displayProducts(this);
		JPanel panS = view.inputPurchase();
		add(panC, "Center");
		add(panS, "South");
		setTitle("음료자판기");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100,20,600,700);
		setVisible(true);
		setResizable(false);
	}
	public void fullproduct() {
		ProductDAO dao = new ProductDAO();
		ProductVO productVO = null;
		pvoList = dao.select();
		String[] name = {"코카콜라","펩시콜라","밀키스","레쓰비","비락식혜","스프라이트"};
		String[] imagePath = {"image/코카.jpg","image/펩시.jpg","image/밀키스.jpg","image/레쓰비.jpg","image/비락식혜.jpg","image/스프라이트.jpg"};
		int[] price = {1000,1000,1100,800,1500,1000};
		
		for(int i = 0; i<name.length; i++) {
			productVO = new ProductVO();
			productVO.setTrade_name(name[i]);
			productVO.setPrice(price[i]);
			productVO.setTotal_stock(10);
			productVO.setNum(i);
			productVO.setImageName(imagePath[i]);
			pvoList.add(productVO);
		}
	}
	
	public static void main(String args[]) {
		new ProductController();
		
	}
}
