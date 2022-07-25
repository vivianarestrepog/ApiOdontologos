window.addEventListener('load', function () {
    (function(){
      const url = '/odontologos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {

         var table = document.getElementById("odontologoTable");

         for(odontologo of data){
            var odontologoRow = table.insertRow();
            let tr_id = 'tr_' + odontologo.id;
            odontologoRow.id = tr_id;

            let deleteLink='<a id=\"a_delete_'+ odontologo.id + '\"'+' href=\"#\" '
            +'onclick=\"deleteBy('+odontologo.id+')\" class=\"link-danger\">Borrar</a>';

            let updateLink='<a odontologos=\"a_update_'+ odontologo.odontologo + '\"'
            + ' href=\"/update_odontologos.html?odontologoId=' + odontologo.id + '\"'
            + 'onclick=\"update('+ odontologo +')\" class=\"link-danger\">Editar</a>';

            odontologoRow.innerHTML =
                '<td class=\"td_id\">' + odontologo.id + '</td>' +
                '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
                '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                '<td class=\"td_matricula\">' + odontologo.matricula + '</td>'+
                '<td>'+updateLink+'</td>' +
                '<td>'+deleteLink+'</td>';
            };
    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/odontologosList.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })

    })