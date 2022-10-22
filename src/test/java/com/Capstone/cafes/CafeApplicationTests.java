package com.Capstone.cafes;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.Capstone.cafes.domain.Before_buy;
import com.Capstone.cafes.persistence.BasketRepository;
import com.Capstone.cafes.persistence.CategoriesRepository;
import com.Capstone.cafes.persistence.CustomerRepository;
import com.Capstone.cafes.persistence.OrdersRepository;
import com.Capstone.cafes.persistence.ProductImageRepository;
import com.Capstone.cafes.persistence.ProductRepository;
import com.Capstone.cafes.persistence.OptionRepository;

import org.json.simple.JSONObject;
@SpringBootTest
class CafeApplicationTests {
	@Autowired
	private CustomerRepository CustomerRepo;
	@Autowired
	private ProductRepository ProductRepo;
	@Autowired
	private CategoriesRepository CategoRepo;
	@Autowired
	private BasketRepository basketRepo;
	@Autowired
	private ProductImageRepository ProImgRepo;
	@Autowired
	private OptionRepository OptionRepository;
	@Autowired
	private OrdersRepository OrderRepository;
	
	
	@Test
	public void testInsert() {
		
	}
//	@Test
//	public void testInsertProduct() {
//        String str_cate[]= {"而ㅽ뵾","�씪�뼹","�뒪臾대뵒"};
//        Categories cate[]=new Categories[3];
//        for (int i = 0; i < 3; i++) {
//           cate[i]=new Categories();
//           cate[i].setCategoryName(str_cate[i]);
//         CategoRepo.save(cate[i]);
//        }
//     //而ㅽ뵾
//     Product product = new Product();
//     product.setProductName("�븘硫붾━移대끂");
//     product.setPrice(1500);
//     product.setAllSale(false);
//     product.setCategories(cate[0]);
//     ProductRepo.save(product);
//     
//     product = new Product();
//     product.setProductName("移댄럹�씪�뼹");
//     product.setPrice(2000);
//     product.setAllSale(false);
//     product.setCategories(cate[0]);
//     ProductRepo.save(product);
////     
////     //�씪�뼹
//     product = new Product();
//     product.setProductName("�뵺湲곕씪�뼹");
//     product.setPrice(1500);
//     product.setAllSale(false);
//     product.setCategories(cate[1]);
//     ProductRepo.save(product);
//     
//     product = new Product();
//     product.setProductName("�끃李⑤씪�뼹");
//     product.setPrice(2000);
//     product.setAllSale(false);
//     product.setCategories(cate[1]);
//     ProductRepo.save(product);
//
//     product = new Product();
//     product.setProductName("�뵺湲� �뒪臾대뵒");
//     product.setPrice(1500);
//     product.setAllSale(false);
//     product.setCategories(cate[2]);
//     ProductRepo.save(product);
//     
//     product = new Product();
//     product.setProductName("珥덉퐫 �뒪臾대뵒");
//     product.setPrice(2000);
//     product.setAllSale(false);
//     product.setCategories(cate[2]);
//     ProductRepo.save(product);
//}
//	@Test
//	   void insertCustomInfo() {
//	      Customer custom = new Customer();
//	      custom.setUserId("Qwe123");
//	      CustomerRepo.save(custom);
//	      
//	      custom = new Customer();
//	      custom.setUserId("Gcv456");
//	      CustomerRepo.save(custom);
//	      
//	   }
	
//	   @Test
//	   void insertProductInfo() {
//	      //OptionRepository.deleteAll();
//	      Iterable<Product> productList = ProductRepo.findAll();
//	      for(Product p:productList) { //�젣�뭹 �닔
//	         for(int i=0; i<3; i++) { //�궗�씠利� (0=Tall, 1=Grande, 2=Venti)
//	            for(int j=0; j<2; j++) { //�삩�룄 (0=Hot, 1=Ice)
//	               Product_option_info goods=new Product_option_info();
//	               goods.setProduct(p);
//	               goods.setSize(i);
//	               if(p.getCategories().getCategoryName().equals("JUICE") ||
//	                     p.getCategories().getCategoryName().equals("FRAPPUCCINO")
//	                     || p.getProductName().contains("肄쒕뱶")) { //ICE留� �븘�슂
//	                  if(j==1) goods.setTemp(j);
//	                  else continue;
//	               }
//	               else { //ICE, HOT �븘�슂
//	                  goods.setTemp(j);
//	               }
//	               
//	               switch(i) {
//	               case 0:
//	                  goods.setPrice(p.getPrice());
//	                  break;
//	               case 1:
//	                  goods.setPrice(p.getPrice()+500);
//	                  break;
//	               case 2:
//	                  goods.setPrice(p.getPrice()+1000);
//	                  break;
//	               }
//	               OptionRepository.save(goods);
//	            }
//	            
//	         }
//	         
//	      }
//
//	   }
	
	
//	@Test
//  void insertProductInfo() {
//     
//     Iterable<Product> productList = ProductRepo.findAll();
//     for(Product p:productList) { //제품 수
//        for(int i=0; i<3; i++) { //사이즈 (0=Tall, 1=Grande, 2=Venti)
//           for(int j=0; j<2; j++) { //온도 (0=Hot, 1=Ice)
//              Product_option_info goods=new Product_option_info();
//              goods.setProduct(p);
//              goods.setSize(i);
//              if(p.getCategories().getCategoryName().equals("JUICE") ||
//                    p.getCategories().getCategoryName().equals("FRAPPUCCINO")
//                    || p.getProductName().contains("콜드")) { //ICE만 필요
//                 if(j==1) goods.setTemp(j);
//                 else continue;
//              }
//              else { //ICE, HOT 필요
//                 goods.setTemp(j);
//              }
//              
//              switch(i) {
//              case 0:
//                 goods.setPrice(p.getPrice());
//                 break;
//              case 1:
//                 goods.setPrice(p.getPrice()+500);
//                 break;
//              case 2:
//                 goods.setPrice(p.getPrice()+1000);
//                 break;
//              }
//              OptionRepository.save(goods);
//           }
//           
//        }
//        
//     }
//
//	}
	
//	@Test
//	  void AMPM() {
//		Iterable<Orders> orderList = OrderRepository.findAll();
//		for(Orders o:orderList) { 
//			
//				int hour=  o.getOrederDate().getHours();
//				int month=  o.getOrederDate().getMonth()+1;
//				System.out.print("++++++++++++++++++++++++++++++");
//				System.out.print(hour);
//				switch(hour) {
//				case 6:
//				case 7:
//				case 8:
//				case 9:
//				case 10:
//				case 11:
//					o.setDay_time("오전");
//					break;
//				case 12:
//				case 13:
//				case 14:
//				case 15:
//				case 16:
//				case 17:
//				case 18:
//					o.setDay_time("오후");
//					break;
//					
//				default:	
//					o.setDay_time("저녁");
//				}
//				
//				switch(month) {
//				case 3:
//				case 4:
//				case 5:
//					o.setSeason("봄");
//					
//					break;
//				case 6:
//				case 7:
//				case 8:
//		
//					o.setSeason("여름");
//					break;
//				case 9:
//				case 10:
//				case 11:
//					o.setSeason("가을");
//					break;
//				default:	
//					o.setSeason("겨울");
//				}
//				OrderRepository.save(o);
//			}
//		}
	
	@Test
	void makejson() {
		List<Before_buy> Before_buyList=(List<Before_buy>) OrderRepository.findbyBestseller();
		   JSONObject before_buy_jsonObj = new JSONObject();
		   System.out.println("++++++++++++++++");
		  
		   int count =0;
		   for(Before_buy b: Before_buyList) {
			   count++;
			   System.out.println(b.getProduct().getProductName()+"/"+b.getCount());
			   

			   before_buy_jsonObj.put("Bestseller"+count, b.getProduct().getProductName());
			   
			  
			   if(count==6)
				   break;
		   }



		String jsonStr = before_buy_jsonObj.toString();
        File jsonFile = new File("C:\\Users\\home\\Desktop\\새 폴더 (3)\\새 폴더\\cafe\\cafe\\src\\main\\resources\\static\\json\\비회원.json");

       
        try{
        	 BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
        	 writer.write(jsonStr);
	         writer.close();
        }catch(Exception e){
        			e.printStackTrace();
        			System.out.println("에러 발생");
       }
        
	}
	
}
