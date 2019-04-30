var main = {
    init: function () {
        var _this = this;
        $('#btn-post').on('click', function (event) {
            _this.post();
            event.preventDefault();     // 이벤트 전파 방지
        });
    },
    
    post: function () {
        
    }
};

main.init();