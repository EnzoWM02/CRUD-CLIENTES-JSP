
<!-- Not doing anything, just a repo for my functions, should add a CPF validation someday
Actually, I couldn't figure it out how to pass this file by argument to my cliente-form.jsp file.
-->

function validateEmail() {

    let regex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');
    if (!regex.test(document.getElementById("forms").elements["email"].value)) {
        let styles = "#email { background-color: #ff5e5e; }";
        var styleSheet = document.createElement("style");
        styleSheet.innerText = styles;
        document.head.appendChild(styleSheet);
        return false;
    } else {
        let styles = "#cpf { background-color: white; }";
        var styleSheet = document.createElement("style");
        styleSheet.innerText = styles;
        document.head.appendChild(styleSheet);
        return true;
    }
}

function test() {
    console.log("sim");
}