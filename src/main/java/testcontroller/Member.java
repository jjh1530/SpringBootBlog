package testcontroller;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor //빈 생성자
//@AllArgsConstructor
//@RequiredArgsConstructor // fina이 붙은 객체의 생성자
public class Member {
	
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder //ID를 제외하고 넣고 싶을 때 새로운 생성자를 만드는게 아닌  builder()를 이용하여 생성
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
}
