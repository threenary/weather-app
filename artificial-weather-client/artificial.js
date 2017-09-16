$(document).ready(function() {

  $("#cities").change(function(){
    $("#cities option[value='0']").remove();

    $.ajax({
        url: "http://localhost:8080/artificial/"+$( "#cities" ).val(),
        dataType: "json",
        success: function( data, textStatus, jqXHR ){
            $('#weather').empty();
            $('#weather').append(data);
        }
    });
  });

  $.ajax({
      url: "http://localhost:8080/artificial/cities",
      dataType: "json",
      success: function( data, textStatus, jqXHR ){
        $.each(data, function(i, obj) {
            $('#cities').append($('<option>', {
                value: obj.name,
                text: obj.name
            }));
        });
        $("<option>", {value:'0', text:'Select city', selected:true}).prependTo('#cities');
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
