// Obtenemos el query param de la url
const params = new URLSearchParams(window.location.search)
let turnoId = params.get('turnoId')

window.addEventListener('load', function () {

    // Invocamos el get del paciente por id y renderizamos
    getTurnoById(turnoId)

    const formulario = document.querySelector('#update_turno');

    formulario.addEventListener('submit', function (event) {

        var selectOdontologo = document.getElementById("selectOdontologo");
        var valorSelectOdontologo = selectOdontologo[selectOdontologo.selectedIndex].value;
        var selectPaciente = document.getElementById("selectPaciente");
        var valorSelectPaciente = selectPaciente[selectPaciente.selectedIndex].value;

        const formData = {
            id : turnoId,
           fecha: document.querySelector('#fecha').value,
           odontologo: {
              id: valorSelectOdontologo,
           },
           paciente: {
              id: valorSelectPaciente,
           }
        };

        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {

                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Turno success </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 window.location.href = "/get_all_turnos.html";

            })
            .catch(error => {

                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
                     resetUploadForm();
            });
    });


    function resetUploadForm(){
        document.querySelector('#fecha').value = "";
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#dni').value = "";
        document.querySelector('#fecha_ingreso').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/turnosList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();

    function getTurnoById(turnoId){
         const url = '/turnos/' + turnoId;
            const settings = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                }
            }

        fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            mostrarTurno(data)
        });
    }

    function mostrarTurno(turno){
        document.querySelector('#fecha').value = turno.fecha;

        var select = document.getElementById("selectOdontologo");
        var optionOdontologo = document.createElement("option");
        optionOdontologo.text = turno.odontologo.nombre + " " + turno.odontologo.apellido;
        optionOdontologo.value = turno.odontologo.id;
        select.appendChild(optionOdontologo);

        var select = document.getElementById("selectPaciente");
        var optionPaciente = document.createElement("option");
        optionPaciente.text = turno.paciente.nombre + " " + turno.paciente.apellido;
        optionPaciente.value = turno.paciente.id;
        select.appendChild(optionPaciente);
    }
});