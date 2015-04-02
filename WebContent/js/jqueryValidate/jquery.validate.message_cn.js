//定义中文消息
var cnmsg = {
    required: "必需填写项",
    remote: "请修正该字段",
    email: "请输入正确格式的电子邮件",
    url: "请输入合法的网址",
    date: "请输入合法的日期",
    dateISO: "请输入合法的日期 (ISO).",
    number: "请输入合法的数字",
    digits: "只能输入整数",
    creditcard: "请输入合法的信用卡号",
    equalTo: "请再次输入相同的值",
    accept: "请输入拥有合法后缀名的字符串",
    maxlength: jQuery.format("请输入一个长度最多是 {0} 的字符串"),
    minlength: jQuery.format("请输入一个长度最少是 {0} 的字符串"),
    rangelength: jQuery.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
    range: jQuery.format("请输入一个介于 {0} 和 {1} 之间的值"),
    max: jQuery.format("请输入一个最大为 {0} 的值"),
    min: jQuery.format("请输入一个最小为 {0} 的值"),
     
    //自定义验证方法的提示信息
    stringCheck: "用户名只能包括中文字、英文字母、数字和下划线",  
    byteRangeLength: "用户名必须在4-15个字符之间(一个中文字算2个字符)",
    isIdCardNo: "请正确输入您的身份证号码",
};
jQuery.extend(jQuery.validator.messages, cnmsg);


// 字符验证      
jQuery.validator.addMethod("stringCheck", function(value, element) {      
    return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);      
}, "只能包括中文字、英文字母、数字和下划线");  
 
// 中文字两个字节      
jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {      
    var length = value.length;      
    for(var i = 0; i < value.length; i++){      
        if(value.charCodeAt(i) > 127){      
        length++;      
        }      
    }      
    return this.optional(element) || ( length >= param[0] && length <= param[1] );      
}, "请确保输入的值在4-15个字节之间(一个中文字算2个字节)");
 
// 身份证号码验证     
jQuery.validator.addMethod("isIdCardNo", function(value, element) {      
    return this.optional(element) || isIdCardNo(value);      
}, "请正确输入您的身份证号码");
 
/**
 * 身份证号码验证
 */ 
function isIdCardNo(num) { 
   
 var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1); 
 var parityBit=new Array("1","0","X","9","8","7","6","5","4","3","2"); 
 var varArray = new Array(); 
 var lngProduct = 0; 
 var intCheckDigit; 
 var intStrLen = num.length; 
 var idNumber = num; 
   // initialize 
     if ((intStrLen != 15) && (intStrLen != 18)) { 
         return false; 
     } 
     // check and set value 
     for(var i=0;i<intStrLen;i++) { 
         varArray[i] = idNumber.charAt(i); 
         if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) { 
             return false; 
         } else if (i < 17) { 
             varArray[i] = varArray[i] * factorArr[i]; 
         }
     }
        
     if (intStrLen == 18) {
         //check date
         var date8 = idNumber.substring(6,14);
         if (isDate8(date8) == false) {
            return false;
         }
         // calculate the sum of the products 
         for(var i=0;i<17;i++) {
             lngProduct = lngProduct + varArray[i];
         }
         // calculate the check digit
         intCheckDigit = parityBit[lngProduct % 11];
         // check last digit
         if (varArray[17] != intCheckDigit) {
             return false;
         }
     }
     else{   //length is 15
         //check date
         var date6 = idNumber.substring(6,12);
         if (isDate6(date6) == false) {
             return false;
         }
     }
     return true;
 }