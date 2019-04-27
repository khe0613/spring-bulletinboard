var main = {
    init: function(){
        var _this = this;
        $('#btn-logout').on('click', function () {
           _this.logout();
           event.preventDefault();     // 이벤트 전파 방지
        });
    },

    logout: function(){
        location.href='/logout';
    }
};

main.init();