$(document).ready(function () {
    $('#sessionIdProd').click(function session() {
        $.ajax({
            type: "GET", 
            url: 'http://localhost:8080/session/prod',
             crossDomain: true, 
             success: function(data) { 
                $( ".result" ).html( data );
                PagSeguroDirectPayment.setSessionId(data);
            }, error: function(e) { 
                console.error(e); 
            }   
        });
    });
    $('#sessionIdSand').click(function () {
        $.ajax({
            type: "GET", 
            url: 'http://localhost:8080/session/sand',
             crossDomain: true, 
             success: function(data) { 
                $( ".result" ).html( data );
                PagSeguroDirectPayment.setSessionId(data);
            }, error: function(e) { 
                console.error(e); 
            }   
        });
    });
        $("#validar").click(function () {
            var param = {
                cardNumber:'4111111111111111',
                brand:'visa',
                cvv: '123',
                expirationMonth: '12',
                expirationYear: '2030',
                success: function (json) {
                    $( ".token" ).html(json.card.token);
                }, error: function (json) {
                    console.log(json);
                }, complete: function (json) {
                }
            }
            PagSeguroDirectPayment.createCardToken(param);

        });

        $('#lightboxProd').click(function () {
            $.ajax({
                type: "GET", 
                url: 'http://localhost:8080/checkout/lightbox/prod',
                 crossDomain: true, 
                 success: function(data) { 
                     console.log(data);
                    PagSeguroLightbox(data);
                }, error: function(e) { 
                    console.error(e); 
                }   
            });
        });
        $('#lightboxSand').click(function () {
            $.ajax({
                type: "GET", 
                url: 'http://localhost:8080/checkout/lightbox/sand',
                 crossDomain: true, 
                 success: function(data) { 
                     console.log(data);
                    PagSeguroLightbox(data);
                }, error: function(e) { 
                    console.error(e); 
                }   
            });
        });
        $('#checkoutProd').click(function () {
            $.ajax({
                type: "GET", 
                url: 'http://localhost:8080/checkout/redirect/prod',
                 crossDomain: true, 
                 success: function(data) { 
                     console.log(data);
                     window.location.replace(data);
                }, error: function(e) { 
                    console.error(e); 
                }   
            });
        });
        $('#checkoutSand').click(function () {
            $.ajax({
                type: "GET", 
                url: 'http://localhost:8080/checkout/redirect/sand',
                 crossDomain: true, 
                 success: function(data) { 
                     console.log(data);
                     window.location.replace(data);
                }, error: function(e) { 
                    console.error(e); 
                }   
            });
        });
        $('#checkoutTransProd').click(function () {
            ;
            console.log(function session());
            $.ajax({
                type: "GET", 
                url: 'http://localhost:8080/checkout/redirect/sand',
                 crossDomain: true, 
                 success: function(data) { 
                     console.log(data);
                     window.location.replace(data);
                }, error: function(e) { 
                    console.error(e); 
                }   
            });
        });
    });

