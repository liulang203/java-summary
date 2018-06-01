define(function (require) {
    authorize  = require("common/authorize");
    return {
        template: `<div>page not fond  404</div>`,
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
