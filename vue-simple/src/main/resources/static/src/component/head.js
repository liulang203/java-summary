define(function (require) {
    authorize  = require("common/authorize");
    return {
        template: `<h1>DDNET</h1>`,
        data: function () {
            return {
            };
        },
        computed: {
            show: function() {
                return ''
            }
        },
        created: function () {
        }
    };
});
