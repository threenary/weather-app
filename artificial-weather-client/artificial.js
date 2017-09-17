$(document).ready(function() {

    function showData(data, city){
        $("#weather").empty();
        $("#weather").append($('<p align=center><strong>'+city+'</strong></p>'))
        $("#weather").append($('<p><strong>Time:</strong> '+data.date+'</p>'))
        $("#weather").append($('<p><strong>Temperature:</strong> '+data.temp+'°C</p>'))
        $("#weather").append($('<p><strong>Condicion:</strong> '+data.text+'</p>'))
    }

    function loading(){
        $("#weather").empty();
        $("#weather").append($('<p>Loding data...</p>'))
    }

    $(".dropdown-menu").on("click", "li", function(event){
          loading();
          var city = $(this).children("a").attr("id");
          $.ajax({
               url: "http://localhost:8080/artificial/"+city,
               dataType: "json",
               success: function( data, textStatus, jqXHR ){



                     showData(data, city);
               }
          });
    });

    $(".btn-default").click(function(){
        $.ajax({
          url: "http://localhost:8080/artificial",
          dataType: "json",
          success: function( data, textStatus, jqXHR ){
              $('#check').empty();
              $('#check').append(data);
          }
      });
    });

  $.ajax({
      url: "http://localhost:8080/artificial/cities",
      dataType: "json",
      success: function( data, textStatus, jqXHR ){
        $.each(data, function(i, obj) {
            $(".dropdown-menu").append($('<li><a href=# id=' +obj.name+'>'+obj.name+'</a></li>'));
        });

      }
  });


  $(".btn-default").click(function(){
      $.ajax({
        url: "http://localhost:8080/artificial",
        dataType: "json",
        success: function( data, textStatus, jqXHR ){
            $('#check').empty();
            $('#check').append(data);
        }
    });
  });
});
