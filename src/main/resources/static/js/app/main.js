var main = {
    init: function(){
        var _this = this;
        $("#btn-signup").on('click', function(){
           _this.signup();
        });
    },
    signup: function(){
        location.href = "/signup";
    }
};

main.init();