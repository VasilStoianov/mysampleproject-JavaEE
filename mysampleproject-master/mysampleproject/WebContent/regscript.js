// function borderRedUsername(){
//   if (document.getElementById("password1").value != document.getElementById("password2").value) {
//     document.getElementById("password1").style.borderColor = "red";
//     document.getElementById("password2").style.borderColor = "red";
//     alert("Password missmatch!");
//     return false;
//   }
//   return true;
// }
password2 = document.getElementById('password2')
password2.onblur = function() {
  password1 = document.getElementById('password1').value;
  password2 = document.getElementById('password2').value;
 if(password1 != password2){
   alert('password missmatch')
   document.getElementById('password1').style.borderColor="red";
   document.getElementById('password2').style.borderColor="red";
 }
 else if(password1 == password2){
  
  document.getElementById('password1').style.borderColor="black";
  document.getElementById('password2').style.borderColor="black";
}
}

