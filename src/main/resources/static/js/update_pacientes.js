// Obtenemos el query param de la url
const params = new URLSearchParams(window.location.search)
let pacienteId = params.get('pacienteId')
let domicilioId;

window.addEventListener('load', function () {

    // Invocamos el get del paciente por id y renderizamos
    getPacienteById(pacienteId)

    const formulario = document.querySelector('#add_new_paciente');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {

        const formData = {
            id : pacienteId,
            apellido: document.querySelector('#apellido').value,
            nombre: document.querySelector('#nombre').value,
            email: document.querySelector('#email').value,
            dni: document.querySelector('#dni').value,
            fechaIngreso: document.querySelector('#fecha_ingreso').value,
            domicilio : {
                 id: domicilioId,
                 calle: document.querySelector('#calle').value,
                 numero: document.querySelector('#numero').value,
                 localidad: document.querySelector('#localidad').value,
                 provincia: document.querySelector('#provincia').value
            }
        };

        const url = '/pacientes';
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
                     '<strong></strong> Paciente success </div>';

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 window.location.href = "/get_all_pacientes.html";

            })
            .catch(error => {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                         '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                         '<strong> Error, intente nuevamente.</strong> </div>';
                     document.querySelector('#response').innerHTML = errorAlert;
                     document.querySelector('#response').style.display = "block";
                     resetUploadForm();
            })
    });


    function resetUploadForm(){
        document.querySelector('#apellido').value = "";
        document.querySelector('#nombre').value = "";
        document.querySelector('#email').value = "";
        document.querySelector('#dni').value = "";
        document.querySelector('#fecha_ingreso').value = "";
        document.querySelector('#calle').value = "";
        document.querySelector('#numero').value = "";
        document.querySelector('#localidad').value = "";
        document.querySelector('#provincia').value = "";
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/pacientesList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();

    function getPacienteById(pacienteId){

        const url = '/pacientes/' + pacienteId;
        const settings = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            }
        }

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            domicilioId = data.domicilio.id;
            document.querySelector('#apellido').value = data.apellido;
            document.querySelector('#nombre').value = data.nombre;
            document.querySelector('#email').value =  data.email;
            document.querySelector('#dni').value =  data.dni;
            document.querySelector('#fecha_ingreso').value = data.fechaIngreso;
            document.querySelector('#calle').value =  data.domicilio.calle;
            document.querySelector('#numero').value =  data.domicilio.numero;
            document.querySelector('#localidad').value =  data.domicilio.localidad;
            document.querySelector('#provincia').value = data.domicilio.provincia;
        });
    }
});