window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_turnos');

    obtenerOdontologos()
    obtenerPacientes()

    formulario.addEventListener('submit', function (event) {

        var selectOdontologo = document.getElementById("selectOdontologo");
        var valorSelectOdontologo = selectOdontologo[selectOdontologo.selectedIndex].value;
        var selectPaciente = document.getElementById("selectPaciente");
        var valorSelectPaciente = selectPaciente[selectPaciente.selectedIndex].value;

        const formData = {
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
            method: 'POST',
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
                 resetUploadForm();

            })
            .catch(error => {

                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
                     resetUploadForm();})
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

    function obtenerOdontologos(){
         const url = '/odontologos';
            const settings = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                }
            }

        fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            mostrarOdontologos(data)
        });
    }

    function mostrarOdontologos(odontologos){
        var select = document.getElementById("selectOdontologo");
        var option = document.createElement("option");
        option.text = "Seleccione";
        option.value = -1;
        select.appendChild(option);

        for(odontologo of odontologos){
            var option = document.createElement("option");
            option.text = odontologo.nombre + " " + odontologo.apellido;
            option.value = odontologo.id;
            select.appendChild(option);
       }
    }

    function  obtenerPacientes(){
            const url = '/pacientes';
                const settings = {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    }
                }

            fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                mostrarPacientes(data)
            });
    }

    function mostrarPacientes(pacientes){
        var select = document.getElementById("selectPaciente");
        var option = document.createElement("option");
        option.text = "Seleccione";
        option.value = -1;
        select.appendChild(option);

        for(paciente of pacientes){
           var option = document.createElement("option");
           option.text = paciente.nombre + " " + paciente.apellido;
           option.value = paciente.id;
           select.appendChild(option);
        }
    }
});