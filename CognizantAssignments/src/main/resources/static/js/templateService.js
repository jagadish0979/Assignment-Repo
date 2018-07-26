angular
.module('app').service('TemplateService', function () {
        var template = 'views/loginHeader.html';
        this.getTemplate = function () {
            return template;
        };
        this.setTemplate = function (newTemplate) {
        	template = newTemplate;
        };
    });