define(function (require) {
    authorize  = require("common/authorize");
    return {
        template: `<div>this is body</div>`,
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
