window.addEventListener('load', function () {
    (function(){
      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {

        var table = document.getElementById("pacienteTable");

         for(paciente of data){
            var pacienteRow = table.insertRow();
            let tr_id = 'tr_' + paciente.id;
            pacienteRow.id = tr_id;


            let deleteLink='<a id=\"a_delete_'+ paciente.id + '\"'+' href=\"#\" '
            + 'onclick=\"deleteBy('+ paciente.id+ ')\" class=\"link-danger\">Borrar</a>';

            let updateLink='<a pacientes=\"a_update_'+ paciente.paciente + '\"'
            + ' href=\"/update_pacientes.html?pacienteId=' + paciente.id + '\"'
            + 'onclick=\"update('+ paciente +')\" class=\"link-danger\">Editar</a>';

            pacienteRow.innerHTML =
                    '<td class=\"td_id\">' + paciente.id + '</td>' +
                    '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_email\">' + paciente.email + '</td>' +
                    '<td class=\"td_DNI\">' + paciente.dni + '</td>'+
                    '<td class=\"td_F.Ingreso\">' + paciente.fechaIngreso + '</td>'+
                    '<td class=\"td_domicilio\">' + paciente.domicilio.calle +
                          " " + paciente.domicilio.numero +
                          ", " + paciente.domicilio.localidad +
                          " " + paciente.domicilio.provincia + '</td>' +
                    '<td>'+ updateLink +'</td>' +
                    '<td>'+ deleteLink +'</td>';
        };
    });
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/pacientesList.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })
})