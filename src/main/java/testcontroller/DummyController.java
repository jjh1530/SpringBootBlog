package testcontroller;

import java.awt.print.Pageable;
import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@RestController
public class DummyController {

	@Autowired //메모리에 로딩 의존성 주입
	private UserRepository userRepository;
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return "삭제 실패";
		}
		
		return "삭제 id : " +id;
	}
	
	@Transactional //함수가 쫑료시에 들어있는 데이터 자동 commit
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) throws IllegalAccessException{
		System.out.println(id);
		System.out.println(requestUser.getPassword());
		System.out.println(requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalAccessException>() {
			@Override
			public IllegalAccessException get() {
				return new IllegalAccessException("수정 실패");
			}
		});
		//더티체킹 영속화된 데이터르루 Transcational을 이용해 저장
		user.setUsername(requestUser.getUsername());
		user.setEmail(requestUser.getEmail());
		user.setPassword(requestUser.getPassword());
		//userRepository.save(requestUser);
		return user;
	}
	
	//한 페이지당 2건의 데이터를 리턴
	@GetMapping("/dummy/user")
	public List<User> pageList() {
		PageRequest pageable = PageRequest.of(0, 2);
		Page<User> users = userRepository.findAll(pageable);
		List<User> user = users.getContent();
		return user;
	}
	
	//{id} 주소를 파라메터를 전달 받을 수 있음
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) throws IllegalAccessException {
		//Optional 데이터베이스에서 차짖못하면 null이기 때문에 null 인지 판단해줌
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalAccessException>() {
			@Override
			public IllegalAccessException get() {
				return new IllegalAccessException("해당 유저는 없음 id : " +id);
			}
		});
		// 요청 : 웹 브라우저
		// user 객체 = 자바 오브젝트
		// 변환 (웹 브라우저가 이해하는 데이터) -> Json(원래는 Gson 사용)
		// 스프링부트 = MessageConvert : 응답시에 자동 작동
		// 자바 오브젝트를 리턴하면 MessageConveter가 Jackson라이브러리 호출
		return user;
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("username" + user.getUsername());
		System.out.println("password" + user.getPassword());
		System.out.println("email" + user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입";
	}
}
