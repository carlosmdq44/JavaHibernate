var oculto = true;

function verDetalle(){
  if(oculto == true){
    mostrarDetalle();
  }
  else{
    ocultarDetalle();
  }
}

function mostrarDetalle(){
  document.getElementById("detalle").style.display="inline";
  document.getElementById("btnDetalle").value = "Ocultar detalle";
  oculto = false;
}

function ocultarDetalle(){
  document.getElementById("detalle").style.display="none";
  document.getElementById("btnDetalle").value = "Ver detalle";
  oculto = true;
}

//Funcion para seleccionar o deseleccinar todos los checkboxes de un formulario
function selectAllCheckboxes( controladorCheckbox ){
  //alert("controladorCheckbox:" + controladorCheckbox.checked);
  //
  //Recuperamos la forma y el arreglo de checkboxes
  var forma = document.getElementById("form1");
  var alumnos = forma.alumnos;

  //El checkboxControlador define si se activan o desactivan los elementos individuales
  //Verificamos que sea un arreglo
  if(alumnos.length > 0){
    for(i=0; i<alumnos.length; i++){
      alumnos[i].checked = controladorCheckbox.checked;
    }
  }
  else{
    if(alumnos != null){
      alumnos.checked = controladorCheckbox.checked;
    }
  }

}

//Validacion del formulario de Alumnos
function validaFormularioListadoAlumnos( boton ){
  //alert(boton.value);
  //Recuperamos la formay la accion para modificarla
  var forma = document.getElementById("form1");
  var accion = document.getElementById("accion");

  if( boton.value == "Agregar" ){
    accion.value = "agregarAlumno";
  }
  else if( boton.value == "Editar"){
    //Solo debe haber un elemento seleccionado
    if(this.validaUnicoCheckbox()){
      accion.value = "editarAlumno";
    }
    else{
      alert("Debe seleccionar solo un elemento a Editar");
      return false;
    }
  }
  else if( boton.value == "Eliminar"){
    accion.value = "eliminarAlumno";
  }
  else if( boton.value == "Buscar"){
	accion.value = "buscarAlumno";
  }


  //Enviamos el formulario
  forma.submit();
}

//Validacion de solo un checkbox seleccionado
function validaUnicoCheckbox(){
  //Recuperamos la forma y el arreglo de checkboxes
  var forma = document.getElementById("form1");
  var alumnos = forma.alumnos;

  //Verificamos si se selecciono mas de un elemento
  contadorCheckboxes = 0;

  //Verificamos que sea un arreglo
  if(alumnos.length > 0){
    //Incrementamos el contador por cada
    for(i=0; i<alumnos.length; i++){
      if( alumnos[i].checked ){
        contadorCheckboxes++;
      }
    }
  }
  else{
    //Esto sucede cuando solo hay un elemento en la tabla, hay que recordar
    //que dinamicamente podrian eliminarse las alumnos y solo dejar un registro
    //por lo que ya no genera un arreglo en javascript, sino un checkbox aislado
    if(alumnos != null && alumnos.checked){
      contadorCheckboxes++;
    }
  }

  //Solo se debio haber seleccionado un elemento
  if(contadorCheckboxes == 1)
    return true;
  else
    return false;
}

/*Regresamos al listado*/
function cancelar(){
  var forma = document.getElementById("form1");
  //Actualizamos el URL que tenia asignado el formulario
  //Para solicitar nuevamente el listado de alumnos, ya que estamos cancelando la modificacion
  window.location = forma.contexto.value + "/ServletControlador";
  forma.accion.value = "listarAlumnos";
  forma.submit();
}

//Este evento nos permite detectar cuando damos click a una celda
function editaRegistro( renglonSeleccionado ){
  //alert(renglonSeleccionado);
  //Recuperamos la formay la accion para modificarla
  var forma = document.getElementById("form1");
  var accion = document.getElementById("accion");

accion.value = "editarAlumno";
forma.alumnos[renglonSeleccionado-1].checked = true;

//Enviamos el formulario
forma.submit();
}

