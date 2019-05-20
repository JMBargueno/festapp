
//Encontrar ruta actual
var ruta = window.location.pathname;

//alert(ruta);
//Switch para darle el active a su correspondiente
switch (ruta) {
    case '/index':
        var elemento = document.getElementById("index");
        elemento.className += " active";
        break;
    case '/':
        var elemento = document.getElementById("index");
        elemento.className += " active";
        break;
    case '/admin/users':
    var elemento = document.getElementById("adminzone");
    elemento.className += " active";
    break;
    
    case 'admin/users':
    var element = document.getElementById("adminzone");
    var elemento = document.getElementById("dropdownId");

    elemento.className += " active";
    element.className += " active";
    break;
}


//Bajar volumen música de bienvenida
var vid = document.getElementById("welcomes");
vid.volume = 0.02;