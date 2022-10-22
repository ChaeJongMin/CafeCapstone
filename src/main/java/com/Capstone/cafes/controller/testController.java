/*
 * package com.example.demo.controller;
 * 
 * import java.io.BufferedOutputStream;
 * 
 * import java.io.BufferedReader; import java.io.BufferedWriter; import
 * java.io.DataOutputStream; import java.io.File; import
 * java.io.FileInputStream; import java.io.FileOutputStream; import
 * java.io.IOException; import java.io.InputStreamReader; import
 * java.io.OutputStream; import java.io.OutputStreamWriter; import
 * java.net.HttpURLConnection; import java.net.URL; import
 * java.net.http.HttpHeaders; import java.nio.file.Files; import
 * java.nio.file.Path; import java.nio.file.Paths; //import
 * org.apache.commons.codec.binary.Base64;
 * 
 * 
 * 
 * import javax.swing.filechooser.FileSystemView;
 * 
 * import org.apache.commons.io.FileUtils;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpEntity; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
 * import org.springframework.stereotype.Controller; import
 * org.springframework.util.LinkedMultiValueMap; import
 * org.springframework.util.MultiValueMap; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController; import
 * org.springframework.web.client.RestTemplate;
 * 
 * @Controller
 * 
 * @RequestMapping(path = "/cafe") public class testController { private final
 * String boundary = "*****"; private final String crlf = "\r\n"; private final
 * String twoHyphens = "--";
 * 
 * @GetMapping("/test") public String sendData( ) throws IOException{
 * 
 * String rootpath=
 * FileSystemView.getFileSystemView().getDefaultDirectory().toString(); String
 * sts=
 * "\\workspace-spring-tool-suite-4-4.12.0.RELEASE\\cafe\\src\\main\\resources\\static\\image\\flower.jpg";
 * String filepath=rootpath+sts; String inputLine=null; StringBuffer
 * stringBuffer=new StringBuffer();
 * 
 * 
 * URL url=new URL("http://e269-35-236-225-11.ngrok.io/gets"); //URL객체 생성
 * 
 * 
 * 
 * //File file = new File(filepath); HttpURLConnection
 * conn=(HttpURLConnection)url.openConnection(); //url주소를 가지고 Http 커넥션 객체 생성
 * 
 * System.out.println(conn.toString()); conn.setDoOutput(true);
 * conn.setDoInput(true); conn.setRequestMethod("POST");
 * conn.setUseCaches(false); // conn.setRequestProperty("Connection",
 * "Keep-Alive"); // conn.setRequestProperty("Cache-Control", "no-cache"); //
 * conn.setRequestProperty( // "Content-Type", "multipart/form-data;boundary=" +
 * this.boundary); conn.setRequestProperty("Content-Type",
 * "application/json; utf-8"); //JSON 보내기 conn.setRequestProperty("Accept",
 * "application/json"); //JSON 보내기
 * 
 * 
 * conn.setConnectTimeout(10000); conn.setReadTimeout(10000); DataOutputStream
 * os = new DataOutputStream(conn.getOutputStream()); // String
 * fileName=file.getName(); // System.out.println(fileName); //
 * System.out.println(file.toPath());
 * 
 * byte[] fileContent = FileUtils.readFileToByteArray(new File(filepath));
 * System.out.println("바이트 변환 된값: "+fileContent); String encodedStrings
 * =Base64.encodeBase64String(fileContent);
 * System.out.println("인코딩 된값: "+encodedStrings);
 * 
 * 
 * // 이미지 경로명 보내기 // // String test="test"; // os.writeBytes(this.twoHyphens +
 * this.boundary + this.crlf); //
 * os.writeBytes("Content-Disposition: form-data; name=\"" + // test +
 * "\";filename=\"" + // fileName + "\"" + this.crlf); //
 * os.writeBytes(this.crlf); // // byte[] bytes =
 * Files.readAllBytes(file.toPath()); // os.write(bytes); // // //
 * os.writeBytes(this.crlf); // os.writeBytes(this.twoHyphens + this.boundary +
 * // this.twoHyphens + this.crlf); // // os.flush(); // os.close(); // json 보내기
 * String jsonInputString = "{name: test, text: "+ encodedStrings+"}";
 * System.out.println(jsonInputString); try(OutputStream os1 =
 * conn.getOutputStream()) { byte[] input = jsonInputString.getBytes("utf-8");
 * os1.write(input, 0, input.length); } // DataOutputStream outputStream = new
 * DataOutputStream(conn.getOutputStream()); //
 * outputStream.writeBytes(twoHyphens + boundary + lineEnd); //
 * outputStream.writeBytes("Content-Disposition: form-data; name=\"" + filefield
 * + "\"; filename=\"" + "flower.img" +"\"" + lineEnd); //
 * outputStream.writeBytes("Content-Type: image/jpeg" + lineEnd); //
 * outputStream.writeBytes("Content-Transfer-Encoding: binary" + lineEnd); //
 * outputStream.writeBytes(lineEnd);
 * 
 * //데이터 전송 // BufferedWriter bWriter=new BufferedWriter(new
 * OutputStreamWriter(conn.getOutputStream(),"UTF-8")); // bWriter.write("123");
 * 
 * //전송된 결과를 읽어옴 BufferedReader bReader=new BufferedReader(new
 * InputStreamReader(conn.getInputStream(),"UTF-8"));
 * while((inputLine=bReader.readLine())!=null){ stringBuffer.append(inputLine);
 * } // bWriter.flush(); // bWriter.close(); bReader.close(); conn.disconnect();
 * return "/cafe/test"; }//sendJSON() }
 */