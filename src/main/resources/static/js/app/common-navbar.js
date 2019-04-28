var main = {
    init: function(){
        var _this = this;
        $('#btn-logout').on('click', function (event) {
           _this.logout();
           event.preventDefault();     // 이벤트 전파 방지
        });

        $('#btn-mypage').on('click', function (event) {
            _this.getMyPage();
            event.preventDefault()
        });
    },

    logout: function(){
        location.href='/logout';
    },
    
    getMyPage: function () {
        //location.href = '//';
    }
};

main.init();