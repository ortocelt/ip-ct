 setTimeout(function(){
	 alert("Isteklo vrijeme");
        window.location.href="http://localhost:8085/CentarZaTestiranje/index.jsf";//the url that will be redirected too.
    }, 300000);// the bigger the number the longer the delay.
 
 function submit(){
	 $('#baton').trigger('click');
 }
 
 