jQuery.validator.addMethod("isZipCode", function(value, element) {  
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的邮政编码");

jQuery.validator.addMethod("mobilezh", function(value, element) {  
    var mobile = /^1\d{10}$/;
    return this.optional(element) || (mobile.test(value));
}, "请正确填写您的手机号");

jQuery.validator.addMethod("loginname", function(value, element) {  
    var loginname =/^[\w\.\_\-\:]{1,20}$/;    
    return this.optional(element) || (loginname.test(value));
}, "请输入1-20位字符(数字、字母、.、_、-、:)");

jQuery.validator.addMethod("positivenum", function(value, element) {  
    var positivenum = /^(0|[1-9]\d*)$/;
    return this.optional(element) || (positivenum.test(value));
}, "请输入非负整数");

jQuery.validator.addMethod("storeDomain", function(value, element) {  
	var storeDomain =/^[a-zA-Z0-9]{2,10}$/;    
	return this.optional(element) || (storeDomain.test(value));
}, "请输入2-10位字符(数字、字母)");

jQuery.validator.addMethod("float", function(value, element) {  
	var float =/^[0-9]+([.]{1}[0-9]+){0,1}$/;    
	return this.optional(element) || (float.test(value));
}, "请输入小数");

jQuery.validator.addMethod("positive", function(value, element) {  
	var positive =/^(([1-9]+[0-9]*.{1}[0-9]+)|([0].{1}[1-9]+[0-9]*)|([1-9][0-9]*)|([0][.][0-9]+[1-9]*))$/;    
	return this.optional(element) || (positive.test(value));
}, "请输入大于零的数字");

jQuery.validator.addMethod("writing", function(value, element) {  
	var positive =/^.{2,10}$/;    
	return this.optional(element) || (positive.test(value));
}, "请输入2~20个文字");