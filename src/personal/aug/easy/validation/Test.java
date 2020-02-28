package personal.aug.easy.validation;

import java.util.HashMap;
import java.util.Map;

import personal.aug.easy.validation.annotations.ValidateByteArray;
import personal.aug.easy.validation.annotations.ValidateDate;
import personal.aug.easy.validation.annotations.ValidateNotNull;
import personal.aug.easy.validation.annotations.ValidateNumber;
import personal.aug.easy.validation.annotations.ValidateString;
import personal.aug.easy.validation.process.ProcessResult;
import personal.aug.easy.validation.supporttypes.Status;

public class Test extends EasyValidation {

	@ValidateNotNull
	private Object o;
	
	@ValidateNotNull
	@ValidateString(match = "\\d+")
	private String s;
	
	@ValidateDate(pattern = "dd/MM/yyyy", min = "11/11/2001", max = "01/01/2022")
	private String date;
	
	@ValidateNumber(min = 20, max = 100)
	private Integer n;
	
	@ValidateByteArray
	private byte[] bytes;
	
	public Object getO() {
		return o;
	}

	public void setO(Object o) {
		this.o = o;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getN() {
		return n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public static void main(String[] args) throws Exception {
		Test t = new Test();
		t.setO(5);
		t.setS("44444");
		t.setDate("01/01/2021");
		t.setN(21);
		t.setBytes("a".getBytes());
		
		Map<String, Object> result = t.validate();
		for (Map.Entry<String, Object> obj : result.entrySet()) {
			System.out.print(obj.getKey() + "=> ");
			
			if (obj.getValue() instanceof Map<?, ?>) {
				Map<String, Object> validateResult = (Map<String, Object>) obj.getValue();
				for (Map.Entry<String, Object> obj2 : validateResult.entrySet()) {
					ProcessResult rs = (ProcessResult) obj2.getValue();
					System.out.println(obj2.getKey() + ": " + rs.getStatusList());
				}
			} else {
				System.out.println(obj.getValue());
			}
		}
		
		System.out.println("ALL ARE VALID: " + result.get(Status.ALL_ARE_VALID.getCode()));
		User user = new User();
		user.setId("123456");
		user.setAge(25);
		user.setEmail("myangelshh@gmail.com");
		System.out.println(user.toString());
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("USER_ID", "abcxyz");
		map.put("AGE", 30);
		map.put("EMAIL", "abc@gmail.com");
		user.fromMap(map);
		System.out.println(user.toString());
		
		
		/*SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.format(new Date());
			
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
		}
		
		Date d = sdf.parse("50/13f/19752r");
		System.out.println(sdf);
		System.out.println(d);*/
	}
}
