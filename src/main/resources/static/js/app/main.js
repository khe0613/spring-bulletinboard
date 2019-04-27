var main = {
    init: function(){
        var _this = this;
        $("#btn-signup").on('click', function(){    // 회원가입 버튼 클릭
           _this.signup();
            event.preventDefault();         // 이벤트 전파 방지
        });

        $('#btn-login').on('click', function(){     // 로그인 버튼 클릭
            _this.login();
            event.preventDefault();         // 이벤트 전파 방지
        });
    },

    signup: function(){
        location.href = "/signup";
    },

    login: function(){

    }
};

main.init();