// Obtenemos el query param de la url
const params = new URLSearchParams(window.location.search)
let odontologoId = params.get('odontologoId')

window.addEventListener('load', function () {

    // Invocamos el get del paciente por id y renderizamos
    getOdontologoById(odontologoId)

    const formulario = document.querySelector('#add_new_odontologo');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {

        const formData = {
            id : odontologoId,
            apellido: document.querySelector('#apellido').value,
            nombre: document.querySelector('#nombre').value,
            matricula: document.querySelector('#matricula').value,
        }

        const url = '/odontologos';
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
                 window.location.href = "/get_all_odontologos.html";

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
         document.querySelector('#matricula').value = "";

    }


    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/odontologosList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
      })();

    function getOdontologoById(odontologoId){

        const url = '/odontologos/' + odontologoId;
        const settings = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            }
        }

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            document.querySelector('#apellido').value = data.apellido;
            document.querySelector('#nombre').value = data.nombre;
            document.querySelector('#matricula').value =  data.matricula;
        })
    }
});