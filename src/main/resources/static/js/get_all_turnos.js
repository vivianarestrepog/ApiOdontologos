window.addEventListener('load', function () {
    (function(){
      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {

        var table = document.getElementById("turnoTable");

         for(turno of data){
            var turnoRow = table.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;


            let deleteLink='<a id=\"a_turno_'+ turno.id + '\"'+' href=\"#\" '
            + 'onclick=\"deleteBy('+ turno.id+ ')\" class=\"link-danger\">Borrar</a>';


            let updateLink='<a turnos=\"a_update_'+ turno.turno + '\"'
            + ' href=\"/update_turnos.html?turnoId=' + turno.id + '\"'
            + 'onclick=\"update('+ turno +')\" class=\"link-danger\">Editar</a>';

            turnoRow.innerHTML =
                    '<td class=\"td_id\">' + turno.id + '</td>' +
                    '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
                     '<td class=\"td_odontologo\">' + turno.odontologo.nombre +
                      " " + turno.odontologo.apellido + '</td>' +
                     '<td class=\"td_paciente\">' + turno.paciente.nombre +
                      " " + turno.paciente.apellido + '</td>' +
                    '<td>'+ updateLink +'</td>'
                     + '<td>'+ deleteLink +'</td>';

        };
    });
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/turnosList.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })
})