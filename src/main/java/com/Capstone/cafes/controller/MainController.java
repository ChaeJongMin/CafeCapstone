package com.Capstone.cafes.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.Capstone.cafes.domain.*;
import org.apache.tomcat.util.codec.binary.Base64;

import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

 
 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Capstone.cafes.domain.*;
import com.Capstone.cafes.persistence.BasketRepository;
import com.Capstone.cafes.persistence.CategoriesRepository;
import com.Capstone.cafes.persistence.CustomerRepository;
import com.Capstone.cafes.persistence.OptionInfoRepo;
import com.Capstone.cafes.persistence.OrdersRepository;
import com.Capstone.cafes.persistence.ProductRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


import java.nio.file.Files;


@Controller // This means that this class is a Controller
@RequestMapping(path = "/cafe") 
public class MainController {
	@Autowired
	private CategoriesRepository CategoriRepo;
	@Autowired
	private CustomerRepository CustomerRepo;
	
	
	@Autowired
	private ProductRepository ProductRepo;
	@Autowired
	private OptionInfoRepo OptionRepo;
	@Autowired
	private BasketRepository BasketRepo;
	
	@Autowired
	private OrdersRepository OrdersRepo;
	
	
	
//	@GetMapping("/menu")
//	public String menuShow(Model model) {
//		System.out.println(" @@GetMapping ?????? ????????? called...");
//		// ?????? ????????????
//		Iterable<Categories> cateList=CategoriRepo.findAll();
//	
//		model.addAttribute("cateList", cateList);
//		// ?????? ??????????????? ?????? ??????
//		
//		return "/cafe/menu";	
//	}
//	@PostMapping("/menu")
//	public String getOption(Model model,@RequestParam("pname") String pname,
//	          @RequestParam("size") String size,@RequestParam("temp") String temp ) {
//		System.out.println(" @@????????? ???...");
//		// ?????? ????????????
//		System.out.println("?????????: "+pname+" ?????????: "+size+" ??????: "+temp);		
//		// ?????? ??????????????? ?????? ??????
//		
//		return "/cafe/menu";
//	}
	
	 
	@GetMapping("/menuManager")
	   public String menuManagerShow(Model model, String carNum, HttpSession session) {
	      System.out.println(" @@GetMapping ?????? ????????? called...");
	      //Customer c = (Customer)session.getAttribute("user");
	      
	      // ?????? ????????????
	      Iterable<Categories> cateList=CategoriRepo.findAll();
	      model.addAttribute("cateList", cateList);
	      
	      Customer c1=(Customer)session.getAttribute("user");
			 
	      model.addAttribute("user",session.getAttribute("user"));
	      // ?????? ??????????????? ?????? ??????
	      
	      //?????? ?????? ?????? ??????
	      System.out.println("************************");
	      List<String> recoList = new ArrayList<String>();
//	      System.out.println(c1);
	      try{
	        	 
	        
		      if(c1==null) {
		    	  carNum="?????????";
		    	  
		    	  Object ob = new JSONParser().parse(new FileReader("C:\\Users\\home\\Desktop\\??? ?????? (3)\\??? ??????\\cafe\\cafe\\src\\main\\resources\\static\\json\\"+carNum+".json"));
		    		
		          // typecasting ob to JSONObject
		          JSONObject js = (JSONObject) ob;
		          recoList.add((String) js.get("Bestseller1"));
		          recoList.add((String) js.get("Bestseller2"));
		          recoList.add((String) js.get("Bestseller3"));
		          recoList.add((String) js.get("Bestseller4"));
		          recoList.add((String) js.get("Bestseller5"));
		          recoList.add((String) js.get("Bestseller6"));
		          
		          
		          System.out.println("**********?????? ??????*****************");
		          System.out.println(recoList.toString());
		      
		      
		      }
		      else {
		    	  carNum=c1.getUserId();
		    	  
		    	  Object ob = new JSONParser().parse(new FileReader("C:\\Users\\home\\Desktop\\??? ?????? (3)\\??? ??????\\cafe\\cafe\\src\\main\\resources\\static\\json\\"+carNum+".json"));
		    		
		          // typecasting ob to JSONObject
		          JSONObject js = (JSONObject) ob;
		          recoList.add((String) js.get("pca_reco1"));
		          recoList.add((String) js.get("pca_reco2"));
		          recoList.add((String) js.get("pca_reco3"));
		          recoList.add((String) js.get("order2vec_reco1"));
		          recoList.add((String) js.get("order2vec_reco2"));
		          recoList.add((String) js.get("order2vec_reco3"));
		          
		          
		          System.out.println("**********?????? ??????*****************");
		          System.out.println(recoList.toString());
		          
		    	  
		    	  
		    	  
		      	}
	      }catch(Exception e){
  			e.printStackTrace();
  			System.out.println("?????? ??????");
	      }
	      
	      
	      System.out.println("beforeBuy login");
	      
	      int carExist = 0;
	      Iterable<Customer> cusList = CustomerRepo.findAll();
	      Customer cus=null;
	      for (Customer c : cusList) {
	            if (carNum.equals(c.getUserId())) {
	            	
	            	 if(c1==null) {
	            		 session.setAttribute("user", c);
	 	    	      	model.addAttribute("user", c);
	            	 }
	       	    	  
	            	
	               carExist = 1;
	               System.out.println(carNum + " exist");
	               cus=c;
	               break;
	            }
	         }

	         System.out.println("beforeBuy list");
	         List<Orders> orderList = new ArrayList<Orders>();
	         List<Product> beforeProd = new ArrayList<Product>();
	         List<Product> recoProd = new ArrayList<Product>(); 
	         
	         for(String r:recoList) {
	        	 
	        	 recoProd.add((Product)ProductRepo.findByProductName(r));
	        	 
	         }
	         
	         System.out.println("//////////////////////");
//	         System.out.println(recoProd.get(0).getProductName());
//	         System.out.println(recoProd.get(1).getProductName());
//	         System.out.println(recoProd.get(2).getProductName());
//	         System.out.println(recoProd.get(3).getProductName());
//	         System.out.println(recoProd.get(4).getProductName());
	         
	         
	         
	         int exist=0; //??????x
	          for(Orders o : cus.getOrdersList()) { 
	             orderList.add(o);
	             exist=0; //??????x
	             for(Product p : beforeProd) {
	                if(o.getProduct().getProductName() == p.getProductName()) {
	                   exist=1;
	                }
	             }
	             if(exist == 0) {
	                beforeProd.add(o.getProduct());  
	             }
	         }
	          
	         System.out.println("orderlist size "+orderList.size());
	         System.out.println("productlist size"+beforeProd.size());
	         
	         model.addAttribute("orderList", orderList);
	         model.addAttribute("beforeProd", beforeProd);
	         model.addAttribute("recoProd", recoProd);
	         

	      //?????? ?????? ?????? ??????
	/*      
	      System.out.println(c.getUserId());
	      System.out.println("beforeBuy list");
	      
	         List<Orders> orderList = new ArrayList<Orders>();
	         List<Product> beforeProd = new ArrayList<Product>();
	         int exist=0; //??????x
	          for(Orders o : cus.getOrdersList()) { 
	             orderList.add(o);
	             for(Product p : beforeProd) {
	                if(o.getProduct().getProductName() == p.getProductName()) {
	                   exist=1;
	                }
	             }
	             if(exist == 0) {
	                beforeProd.add(o.getProduct());  
	             }
	         }
	          
	       if(orderList.size()==0) {
	          System.out.println(c.getUserId());
	          System.out.println("???????????? ??????");
	       }
	       else {
	          System.out.println(c.getUserId());
	          System.out.println("???????????? ??????");
	          System.out.println(beforeProd.size());
	       }
	      
	      model.addAttribute("orderList", orderList);
	      model.addAttribute("beforeProd", beforeProd);
	      */
	      
	      return "/cafe/menuManager";   
	   }
	
	
//	@PostMapping("/menuManager")
//	public String getOption(Model model,@RequestParam("pname") String pname,
//	          @RequestParam("size") String size,@RequestParam("temp") String temp ) {		
//		// ???????????? ?????? ?????? ??????		
//		System.out.println(" @@????????? ???...");
//		// ?????? ????????????		
//		System.out.println("?????????: "+pname+" ?????????: "+size+" ??????: "+temp);		
//		Product_option_info getProdInfo=OptionRepo.findOptionbyinfos(ProductRepo.findByProductName(pname) ,findSizeToNum(size), findTempToNum(temp)).get(0);
//	
//		
////		Basket saveBasket=new Basket();
////		saveBasket.setPCount(1);
////		saveBasket.setPrice(getProdInfo.getPrice());
////		saveBasket.setProductinfo(getProdInfo);
////		saveBasket.setCustomer(CustomerRepo.findById(1).get());
////		saveBasket.setInfo("test");
////		
////		BasketRepo.save(saveBasket);
//		Product p=new Product();
//		
//		Iterable<Basket> basketList=BasketRepo.findAll();
//	
//		Iterable<Categories> cateList=CategoriRepo.findAll();
//		
//		model.addAttribute("cateList", cateList);
//		
//		model.addAttribute("basketList",basketList);
//		
//		return "redirect:/cafe/menuManager";	
//	}
	 @PostMapping("/menuManager")
	   public String getOption(Model model, @RequestParam String jsonData, HttpSession session ) throws ParseException {      
	      // ???????????? ?????? ?????? ??????      
	       
	      Customer c1=(Customer)session.getAttribute("user");       
	      
	      
	      //?????? pay ??????????????? ?????? ?????? ???????????? ????????? pay???????????? ??? ???
	      //?????? ???????????? ???????????? ?????? ????????? ????????? ??????
	      if(BasketRepo.findByCustomer(c1).size()!=0) {
	         List<Basket> bkList=BasketRepo.findByCustomer(c1);
	         for (Basket pre_b: bkList) {
	            BasketRepo.delete(pre_b);
	         }
	         
	      }
	      //????????? jsonDatafmf JSON ????????? ??????
	      JSONParser parser = new JSONParser();            
	        Object objs = parser.parse(jsonData);
	        JSONArray array = (JSONArray)objs;
	       
	     
	       
	        //???????????? ???????????? ??????
	       for(int i=0; i<array.size(); i++) {
	          JSONObject obj = (JSONObject)array.get(i);
	                   
	          String name=(String)obj.get("name");                    
	          int price= Integer.parseInt((String)obj.get("price"));          
	          String size=(String)obj.get("size");
	          String temp=(String)obj.get("temp");
	          int count=Integer.parseInt(obj.get("count").toString());
	          System.out.println(name+" "+price+" "+size+" "+temp+" "+count);
	         
	          
	          Basket b=new Basket();
	          b.setCustomer(c1);
	          b.setPCount(count);
	          b.setPrice(price);
	                              
	          Product p=ProductRepo.findByProductName(name);
	          Product_option_info pInfo=p.getInfo(findSizeToNum(size), findTempToNum(temp));
	          b.setProductinfo(pInfo);
	          BasketRepo.save(b);          
	           
	       }
	      return "/cafe/pay";   
	   }
	public int findSizeToNum(String mSize) { //????????? ?????? ?????? 
		if(mSize.equals("Tall")) {
			return 0;
		}
		else if(mSize.equals("Grande")) { 
			return 1;
		}
		return 2;
	}
	public int findTempToNum(String mTemp) { //?????? ?????? ??????
		if(mTemp.equals("Hot")) {
			return 0;
		}		
		return 1;
	}
	
	
	@GetMapping("/detail")
	public String detailShow() {
		return "/cafe/detail";	
	}
	
	@GetMapping("/card")
	public String cardShow() {
		return "/cafe/card";	
	}
	
	@GetMapping("/cart")
	public String cartShow() {
		return "/cafe/cart";	
	}
	
	@GetMapping("/mainpage")
	public String mainpageShow() {
		return "/cafe/mainpage";	
	}
	
//	@GetMapping("/pay")
//	public String payShow() {
//		return "/cafe/pay";	
//	}
//	
//	@GetMapping("/paycomplete")
//	public String paycompleteShow() {
//		return "/cafe/paycomplete";	
//	}
	
	@GetMapping("/car")
	public String carShow() {
		return "/cafe/car";	
	}
	
	@GetMapping("/beforeBuy")
	public String beforeBuyShow() {
		return "/cafe/beforeBuy";	
	}
	
	@GetMapping("/recommend")
	public String recommendShow() {
		return "/cafe/recommend";	
	}
	
	@ResponseBody
	@PostMapping("/recommend" )
	public String ImgSaveTest(Model model, @RequestParam HashMap<Object, Object> param,
			@RequestParam("imgSrc") String binaryData,HttpSession session) throws Exception {
			
		String urlstr="http://a6f7-35-199-54-177.ngrok.io";
		FileOutputStream stream = null;
		try{
			System.out.println("binary file   "  + binaryData);
			if(binaryData == null || binaryData.trim().equals("")) {
			    throw new Exception();
			}
			binaryData = binaryData.replaceAll("data:image/png;base64,", "");
			byte[] file1 = Base64.decodeBase64(binaryData);
			String fileName=  UUID.randomUUID().toString();
			
			stream = new FileOutputStream("C:\\Users\\home\\Desktop\\??? ?????? (3)\\??? ??????\\cafe\\cafe\\src\\main\\resources\\static\\img\\"
			+"car_number"+".png");
			stream.write(file1);
			stream.close();
			System.out.println("?????? ?????? "+fileName);
			
			
			
			 File file = new File("C:\\Users\\home\\Desktop\\??? ?????? (3)\\??? ??????\\cafe\\cafe\\src\\main\\resources\\static\\img\\"
						+"car_number"+".png");
	          byte[] fileContent = Files.readAllBytes(file.toPath());
	          System.out.println("byte:"+ fileContent);
	          
	          String encoded = Base64Utils.encodeToString(fileContent);
	          JSONObject jObject=new JSONObject();
	          jObject.put("Test", encoded); //JSON??? ??? ??????

	           String inputLine=null;
	           StringBuffer stringBuffer=new StringBuffer();
	           URL url=new URL(urlstr); //URL?????? ??????
	           
	               
	           HttpURLConnection conn=(HttpURLConnection)url.openConnection(); //url????????? ????????? Http ????????? ?????? ??????
	               
	           System.out.println(conn.toString());
	           conn.setDoOutput(true);
	           conn.setDoInput(true);
	           conn.setRequestMethod("POST");
	           conn.setRequestProperty("Content-Type", "application/json");
	           conn.setRequestProperty("Accept-Charset", "UTF-8");
	           conn.setConnectTimeout(1000000000);
	           conn.setReadTimeout(1000000000);
	               
	         //????????? ??????
	           BufferedWriter bWriter=new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
	           bWriter.write(jObject.toString());
	           bWriter.flush();

	           
	           //????????? ????????? ?????????
	           BufferedReader bReader=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	           while((inputLine=bReader.readLine())!=null){
	               stringBuffer.append(inputLine);
	           }
	           
	           System.out.println(stringBuffer);
	           
	           bWriter.flush();
	           bWriter.close();
	           bReader.close();
	           conn.disconnect();
	           
	           
	           
	        // ?????? ??? JSONObject??? ???????????????.
	           String jsonText =  stringBuffer.toString();
	           
	           JSONParser parser = new JSONParser();
	           
	           JSONObject obj = null;
	           obj = (JSONObject)parser.parse(jsonText);
	           System.out.println(obj.get("car_num"));

	           model.addAttribute("car_num", obj.get("car_num"));
	           String car_num= (String)obj.get("car_num");

//			String car_num= "19???1004";
	       //sendJSON()
	           
	           Iterable<Customer> cusList = CustomerRepo.findAll();
	           
	           for (Customer c : cusList) {
	        	   
	        	   if(car_num.equals(c.getUserId())) {
	        		   session.setAttribute("user", c);
	        		   model.addAttribute("user", c);
	        		   System.out.println("???????????? ??????");
	        		   
	        		   List<Before_buy> Before_buyList=(List<Before_buy>) OrdersRepo.findbyBestMenu(c);
	        		   JSONObject before_buy_jsonObj = new JSONObject();
	        		   System.out.println("++++++++++++++++");
	        		   JSONArray jsonArr1 = new JSONArray();
	        		   int count =0;
	        		   for(Before_buy b: Before_buyList) {
	        			   count++;
	        			   System.out.println(b.getProduct().getProductName()+"/"+b.getCount());
	        			   JSONObject jsonObj1 = new JSONObject();

	        			   jsonObj1.put("product", b.getProduct().getProductName());
	        			   jsonObj1.put("count", b.getCount());
	        			   jsonArr1.add(jsonObj1);
	        		   }
	        		   
	        		   if(count==0) {
	        			   JSONObject jsonObj1 = new JSONObject();
	        			   jsonObj1.put("product", "???????????????");
	        			   jsonObj1.put("count", 1);
	        			   jsonArr1.add(jsonObj1);
	        		   }
	        		   
	        			   before_buy_jsonObj.put("age", c.getAge());
	        			   if(c.getGender().equals("M"))
	        				   before_buy_jsonObj.put("gender", "??????");
	        			   else if(c.getGender().equals("F"))
	        				   before_buy_jsonObj.put("gender", "??????");
	        			   
	        			   Calendar now = Calendar.getInstance();

	        			   int month = now.get(Calendar.MONTH) +1;
	        			   int day = now.get(Calendar.DAY_OF_MONTH);
	        			   
	        			   int hour = now.get(Calendar.HOUR);
	        			   
	        			   switch(hour) {
		       				case 6:
		       				case 7:
		       				case 8:
		       				case 9:
		       				case 10:
		       				case 11:
		       					before_buy_jsonObj.put("day_time", "??????");
		       					
		       					break;
		       				case 12:
		       				case 13:
		       				case 14:
		       				case 15:
		       				case 16:
		       				case 17:
		       				case 18:
		       					before_buy_jsonObj.put("day_time", "??????");
		       					break;
		       					
		       				default:	
		       					before_buy_jsonObj.put("day_time", "??????");
	       					}
	       				
		       				switch(month) {
		       				case 3:
		       				case 4:
		       				case 5:
		       					
		       					before_buy_jsonObj.put("season", "???");
		       					break;
		       				case 6:
		       				case 7:
		       				case 8:
		       		
		       					before_buy_jsonObj.put("season", "??????");
		       					break;
		       				case 9:
		       				case 10:
		       				case 11:
		       					before_buy_jsonObj.put("season", "??????");
		       					break;
		       				default:	
		       					before_buy_jsonObj.put("season", "??????");
		       				}
	        			   
	        			   
	        			   
//	        		   }
	        		   System.out.println(jsonArr1);
//	        		   System.out.println(jsonArr1.get(0));
//	        		   System.out.println(jsonArr1.get(1));
	        		   before_buy_jsonObj.put("before_buy", jsonArr1);
	        		   
	        		   
	        		   System.out.println(before_buy_jsonObj);
	        		   
	        		   
	        		   
	        		   
	     	          
	        		   
	     	           String inputLine2=null;
	     	           StringBuffer stringBuffer2=new StringBuffer();
	     	           URL url2=new URL(urlstr+"/recommendation"); //URL?????? ??????
	     	           
	     	               
	     	           HttpURLConnection conn2=(HttpURLConnection)url2.openConnection(); //url????????? ????????? Http ????????? ?????? ??????
	     	               
	     	           System.out.println(conn2.toString());
	     	           conn2.setDoOutput(true);
	     	           conn2.setDoInput(true);
	     	           conn2.setRequestMethod("POST");
	     	           conn2.setRequestProperty("Content-Type", "application/json");
	     	           conn2.setRequestProperty("Accept-Charset", "UTF-8");
	     	           conn2.setConnectTimeout(1000000000);
	     	           conn2.setReadTimeout(1000000000);
	     	               
	     	         //????????? ??????
	     	           BufferedWriter bWriter2=new BufferedWriter(new OutputStreamWriter(conn2.getOutputStream(),"UTF-8"));
	     	           bWriter2.write(before_buy_jsonObj.toString());
	     	           bWriter2.flush();
	     
	     	           
	     	           //????????? ????????? ?????????
	     	           BufferedReader bReader2=new BufferedReader(new InputStreamReader(conn2.getInputStream(),"UTF-8"));
	     	           while((inputLine2=bReader2.readLine())!=null){
	     	               stringBuffer2.append(inputLine2);
	     	           }
	     	           
	     	           System.out.println(stringBuffer2);
	     	           
	     	           bWriter2.flush();
	     	           bWriter2.close();
	     	           bReader2.close();
	     	           conn2.disconnect();
	        		   
 
	    	           
	     		        // ?????? ??? JSONObject??? ???????????????.
	    	           String jsonText2 =  stringBuffer2.toString();
	    	           
	    	           JSONParser parser2 = new JSONParser();
	    	           
	    	           JSONObject obj2 = null;
	    	           obj2 = (JSONObject)parser2.parse(jsonText2);
	    	           System.out.println("***************?????? ??????**************");
	    	           System.out.println(obj2.get("pca_reco1"));
	    	           System.out.println(obj2.get("pca_reco2"));
	    	           System.out.println(obj2.get("pca_reco3"));
	    	           System.out.println(obj2.get("order2vec_reco1"));
	    	           System.out.println(obj2.get("order2vec_reco2"));
	    	           System.out.println(obj2.get("order2vec_reco3"));
	    	           System.out.println(obj2.get("pca_reco"));
	    	           System.out.println(obj2.get("order2vec_reco"));
	    	           
	    	           String jsonStr = obj2.toString();
	    	           File jsonFile = new File("C:\\Users\\home\\Desktop\\??? ?????? (3)\\??? ??????\\cafe\\cafe\\src\\main\\resources\\static\\json\\"+car_num+".json");

	    	           BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
	    	           writer.write(jsonStr);
	    	           writer.close();
	    	          
	    	           

	        		   return car_num;
	        	   }
	        	   
	        	   
	        	   
	   		
	   		}
	           
	            
//	           Customer c=new Customer();
//	           c.setUserId(car_num);
//	           CustomerRepo.save(c);
//	           session.setAttribute("user", c);
//	           model.addAttribute("user", c);
	         //???????????? customer ????????? ?????? ???
	           model.addAttribute("carNum", car_num);
               session.setAttribute("carNum", car_num);

			
	           System.out.println("???????????? ?????? ??????");
	           
	           
	           
    		   return car_num;
		    
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("?????? ??????");
		}finally{
			if(stream != null) {
				stream.close();
			}
		}
		
		return "/cafe/recommend";	
	}
	
	
	
	@GetMapping("/speech")
	public String speechShow2(Model model) {
		System.out.println(" @@GetMapping ?????? ????????? called...");
		//???????????? ?????? ?????????
		//??? ????????? ?????? ?????????		
		return "/cafe/speech";	
	}
	
	@PostMapping("/get_data")
	public String menShow(@RequestParam("msgs") String msgs) {
		System.out.println(" @@PostMapping ?????? ????????? called...");
		System.out.println(msgs);
		
		return "/cafe/Menu";	
	}
	
	   @GetMapping("/join")
	   public String joinshow(Model model, HttpSession session) {
	      System.out.println("GetMapping join");
	      String car_num = (String)session.getAttribute("carNum");
	      System.out.println(car_num);
	      model.addAttribute("carNum", car_num);
	      return "/cafe/join";
	   }
	   
	   
	   @PostMapping("/join")
	   public String joinPost(Customer user, Model model, HttpSession session) {
	      System.out.println("join PostMapping");
	      System.out.println(user.getGender());
	      String car_num = (String)session.getAttribute("carNum");
	      user.setUserId(car_num);
	      
	      CustomerRepo.save(user);
	      session.setAttribute("user", user);
	      return "/cafe/join";
	   }
	   
	   
	   @GetMapping("/pay")
	   public String payShow(Model model, HttpSession session ) {
	      
	       
	       String[] sizeArr= {"Tall","Grande","Venti"};
	       String[] tempArr= {"Hot", "Ice"};
	       //?????? ????????? ?????? 
	       Customer c=(Customer)session.getAttribute("user");    
	       // ???????????? ?????????
	       List<Basket> bkList=new ArrayList<Basket>();
	       bkList=BasketRepo.findByCustomer(c);
	       // model??? ??????
	       int sum=0;
	       for(Basket b:bkList) {
	          sum+=b.getPrice();
	       }
	       model.addAttribute("basketList",bkList);
	       model.addAttribute("sum",sum);
	       model.addAttribute("sizeArr",sizeArr);
	       model.addAttribute("tempArr",tempArr);
	       Basket b1=new Basket();
	       //b1.getProductinfo().getSize()
	       return "/cafe/pay";   
	   }
	   
	   @GetMapping("/paycomplete")
	   public String paycompleteShow(HttpSession session) {
	       Customer c=(Customer)session.getAttribute("user");
	       List<Basket> bkList=new ArrayList<Basket>();                     
	       bkList=BasketRepo.findByCustomer(c);
	       for (Basket b:bkList) {       
	          Orders o=new Orders();
	          o.setCustomer(c);
	          o.setCount(b.getPCount() );
	          o.setProduct(b.getProductinfo().getProduct());
	          o.setState("True");          
	          
	          Date date=new Date();
	          int hour=date.getHours();
	          int month=date.getMonth()+1;
	          
	          String season=setSeason(month);
	          String days=setDay(hour);
	          o.setOrederDate(date);
	          

	          o.setDay_time(days);
	          o.setSeason(season);
	          
	          
	          
	          OrdersRepo.save(o);
	          System.out.println("********OrdersRepo.save(o);******************");
	          System.out.println(o.getProduct().getProductName()+" "+o.getCustomer().getUserId());
	          System.out.println(o.getOId());
	          BasketRepo.deleteById(b.getBid());
	          System.out.println("********BasketRepo.deleteById(b.getBid());******************");
	       }
	       //?????? ??????????????? ?????? ?????????
	      session.invalidate();    
	      return "/cafe/paycomplete";
	   }
	    
	   public String setSeason(int months) {
	      String seasons="";
	      if(months>=3 && months<=5) {
	         seasons="???";
	      }
	      else if (months>=6 && months<=8){
	         seasons="??????";
	      }
	      else if((months>=9 && months<=11)){
	         seasons="??????";
	      }
	      else {
	         seasons="??????";
	      }
	      return seasons;
	   }
	   public String setDay(int hours) {
	      String days="";
	      if(hours>=6 && hours<12) {
	         days="??????";
	      }
	      else if(hours>=12 && hours <18) {
	         days="??????";
	      }
	      else {
	         days="??????";
	      }
	      return days;
	   }
	   
	   @GetMapping("/logout")
       public String logoutGet(HttpSession session, HttpServletRequest request) {
          session = request.getSession();
          session.invalidate();
          System.out.println("*******?????? ?????????*******");
          System.out.println("*******????????????*******");
          return "/cafe/mainpage";
       }
	  
}