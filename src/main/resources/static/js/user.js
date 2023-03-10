let index = {
	init: function(){
		$("#btn_save").on("click",()=>{  //function(){} , () => {} this를 바인딩 하기 위해
			this.save();
		});
		$("#btn-update").on("click",()=>{  //function(){} , () => {} this를 바인딩 하기 위해
			this.update();
		});
	},
	save: function() {
		//alert("user의 save 함수");
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};
		
		//ajax호출시 default 비동기 호출
		// ajax통신을 이용해 3개의 데이터를 json으로 변경하여 insert 요청
		$.ajax({
			//회원가입 수행 요청
			type : "POST",
			url : "/auth/joinProc",
			data : JSON.stringify(data), //http body 데이터
			contentType : "application/json; charset=utf-8",
			dataType : "json" // 응답 데이터 기본적으로 문자열
		}).done(function(response){
			//웅덥성공
			if(response.status === 500) {
				alert("회원가입 실패");
			}else {
				alert("회원가입 완료");
				location.href="/"
			}
			
		}).fail(function(){
			//응답 실패
			alert("error");
			
		}); 
	},
	update: function() {
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};
		console.log(id);
		$.ajax({
			//회원가입 수행 요청
			type : "PUT",
			url : "/user",
			data : JSON.stringify(data), //http body 데이터
			contentType : "application/json; charset=utf-8",
			dataType : "json" // 응답 데이터 기본적으로 문자열
		}).done(function(response){
			//웅덥성공
			alert("회원수정 완료");
			console.log(response)
			location.href="/"
		}).fail(function(){
			//응답 실패
			alert("error");
			
		}); 
	}
}

index.init();
