let index = {
	init: function(){
		$("#btn-save").on("click",()=>{  //function(){} , () => {} this를 바인딩 하기 위해
			this.save();
		});
		$("#btn-update").on("click",()=>{  //function(){} , () => {} this를 바인딩 하기 위해
			this.update();
		});
		$("#btn-delete").on("click",()=>{  //function(){} , () => {} this를 바인딩 하기 위해
			this.deleteById();
		});
		
	},
	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
		
		//ajax호출시 default 비동기 호출
		// ajax통신을 이용해 3개의 데이터를 json으로 변경하여 insert 요청
		$.ajax({
			//회원가입 수행 요청
			type : "POST",
			url : "/api/board",
			data : JSON.stringify(data), //http body 데이터
			contentType : "application/json; charset=utf-8",
			dataType : "json" // 응답 데이터 기본적으로 문자열
		}).done(function(response){
			//웅덥성공
			alert("글 작성 완료");
			location.href="/"
		}).fail(function(){
			//응답 실패
			alert("error");
			
		}); 
	},
	deleteById: function() {
		let id = $("#id").text();
		console.log(id);
		$.ajax({
			type : "DELETE",
			url : "/api/board/"+id,
			dataType : "json" // 응답 데이터 기본적으로 문자열
		}).done(function(response){
			//웅덥성공
			alert("삭제 완료");
			location.href="/"
		}).fail(function(){
			//응답 실패
			alert("error");
			
		}); 
	},
	update: function() {
		let id = $("#id").text();
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
		
		//ajax호출시 default 비동기 호출
		// ajax통신을 이용해 3개의 데이터를 json으로 변경하여 insert 요청
		$.ajax({
			//회원가입 수행 요청
			type : "POST",
			url : "/api/board"+id ,
			data : JSON.stringify(data), //http body 데이터
			contentType : "application/json; charset=utf-8",
			dataType : "json" // 응답 데이터 기본적으로 문자열
		}).done(function(response){
			//웅덥성공
			alert("글 수정 완료");
			location.href="/"
		}).fail(function(){
			//응답 실패
			alert("error");
			
		}); 
	}
}

index.init();
